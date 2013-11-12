package br.com.maps.labrador;

import static jmine.tec.web.wicket.UrlUtils.addSegmentsIfNotEmpty;
import static jmine.tec.web.wicket.mapper.ImageType.isGif;
import static jmine.tec.web.wicket.mapper.ImageType.isJpeg;
import static jmine.tec.web.wicket.mapper.ImageType.isJpg;
import static jmine.tec.web.wicket.mapper.ImageType.isPng;
import static org.apache.commons.lang.StringUtils.isEmpty;
import jmine.tec.web.wicket.mapper.StaticImageResourceReference;

import org.apache.wicket.Application;
import org.apache.wicket.core.request.mapper.IMapperContext;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.handler.resource.ResourceReferenceRequestHandler;
import org.apache.wicket.request.mapper.AbstractMapper;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.IResourceCachingStrategy;

public class LabradorImageMapper extends AbstractMapper {

    private static final String FILE_SEPARATOR = "/";

    private final String alias;

    private String imageDir;

    private final Class<?> scope;

    /**
     * Construtor
     * 
     * @param alias - o caminho que deve ser mapeado
     * @param scope - a classe escopo para encontrar as imagens
     */
    public LabradorImageMapper(String alias, Class<?> scope) {
        this.alias = alias;
        this.scope = scope;
    }

    /**
     * Construtor
     * 
     * @param alias - o caminho que deve ser mapeado
     * @param scope - a classe escopo para encontrar as imagens
     * @param imageDir - o diretório das imagens
     */
    public LabradorImageMapper(String alias, Class<?> scope, String imageDir) {
        this(alias, scope);
        this.imageDir = imageDir;
    }

    /**
     * {@inheritDoc}
     */
    public IRequestHandler mapRequest(Request request) {
        String url = request.getUrl().toString();  
        // BUGFIX (rafael.volpato): 404 em requests images/view.png?antiCache=7498232        
        url = url.indexOf("?") > 0 ? url.substring(0, url.indexOf("?")) : url;      
        if (this.getCompatibilityScore(request) == Integer.MAX_VALUE) {
            int idx = url.lastIndexOf(FILE_SEPARATOR);
            String filename = idx >= 0 ? url.substring(idx + 1) : url;
            if (!isEmpty(this.imageDir)) {
                filename = this.imageDir + filename;
            }
            ResourceReference reference = new StaticImageResourceReference(this.scope, filename);

            return new ResourceReferenceRequestHandler(reference);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int getCompatibilityScore(Request request) {
        int score = 0;
        String url = request.getUrl().toString();
        url = url.indexOf("?") > 0 ? url.substring(0, url.indexOf("?")) : url;
        if (this.isImage(url) && !this.pathIsVersioned(url) && url.contains(this.alias + FILE_SEPARATOR) && !this.pathIsAntiCached(url)) {
            score = Integer.MAX_VALUE;
        }
        return score;
    }

    /**
     * {@inheritDoc}
     */
    public Url mapHandler(IRequestHandler requestHandler) {
        if (requestHandler instanceof ResourceReferenceRequestHandler) {
            ResourceReference resourceReference = ((ResourceReferenceRequestHandler) requestHandler).getResourceReference();
            if (resourceReference instanceof StaticImageResourceReference) {
                final String extension = resourceReference.getExtension();
                if (!isEmpty(extension) && resourceReference.getScope().equals(this.scope)) {
                    Url url = new Url();

                    final String namespace = this.getContext().getNamespace();
                    final String resourceIdentifier = this.getContext().getResourceIdentifier();
                    final String name = ((ResourceReferenceRequestHandler) requestHandler).getResourceReference().getName();

                    addSegmentsIfNotEmpty(url, namespace, resourceIdentifier, this.alias, name);

                    return url;
                }
            }
        }
        return null;
    }

    /**
     * Diz se caminho pertence à uma imagem
     * 
     * @param path - o caminho
     * @return true se pertencer à uma imagem, false se não.
     */
    private boolean isImage(String path) {
        return isGif(path) || isJpeg(path) || isJpg(path) || isPng(path);
    }

    /**
     * Diz se o caminho já está versionado
     * 
     * @param path - o caminho
     * @return true se estiver versionado, false se não
     */
    private boolean pathIsVersioned(String path) {
        final IResourceCachingStrategy cachingStrategy = Application.get().getResourceSettings().getCachingStrategy();
        if (cachingStrategy instanceof FilenameWithVersionResourceCachingStrategy) {
            return path.contains(((FilenameWithVersionResourceCachingStrategy) cachingStrategy).getVersionPrefix());
        }
        return false;
    }

    /**
     * @return o contexto da aplicação
     */
    protected IMapperContext getContext() {
        return Application.get().getMapperContext();
    }

    /**
     * Diz se o caminho possui algum sistema anti-cache.
     * 
     * @param path - o caminho da imagem
     * @return true se possuir anti-cache, false caso contrário.
     */
    private boolean pathIsAntiCached(String path) {
        return path.contains("antiCache");
    }

}
