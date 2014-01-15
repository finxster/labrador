package br.com.maps.labrador;

import images.ImageResources;

import java.util.Iterator;

import jmine.tec.rtm.impl.RtmController;
import jmine.tec.security.api.SecurityManager;
import jmine.tec.security.web.WebSecurityContext;
import jmine.tec.web.wicket.application.JMineWicketWebApplication;
import jmine.tec.web.wicket.mapper.ImageMapper;
import jmine.tec.web.wicket.pages.main.Logout;
import jmine.tec.web.wicket.security.SecureSession;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.SystemMapper;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.core.util.file.WebApplicationPath;
import org.apache.wicket.markup.html.pages.AccessDeniedPage;
import org.apache.wicket.request.IRequestMapper;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.LastModifiedResourceVersion;
import org.apache.wicket.settings.IResourceSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import br.com.maps.labrador.pages.consulta.emprestavel.ConsultaEmprestavel;
import br.com.maps.labrador.pages.login.LabradorLogin;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;

/**
 * Starting point webApplication
 * 
 * @author takeshi
 */
public class LabradorApplication extends JMineWicketWebApplication {

    private SecurityManager<WebSecurityContext> securityManager;

    private RtmController rtmController;

    private String style;

    private IAuthorizationStrategy authorizationStrategy;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void init() {
        super.init();

        // desligando modo debug
        this.getDebugSettings().setDevelopmentUtilitiesEnabled(false);
        this.getDebugSettings().setAjaxDebugModeEnabled(false);

        // configuração do wicket-bootstrap
        Bootstrap.install(Application.get(), new BootstrapSettings());
        this.getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        this.getSecuritySettings().setAuthorizationStrategy(this.authorizationStrategy);
        this.getApplicationSettings().setAccessDeniedPage(AccessDeniedPage.class);
        IResourceSettings resourceSettings = this.getResourceSettings();
        // resourceSettings.addResourceFolder("");
        resourceSettings.setCachingStrategy(new FilenameWithVersionResourceCachingStrategy(new LastModifiedResourceVersion()));

        this.mountPage("logout", Logout.class);
        this.mountPage("login", LabradorLogin.class);
        this.mountPage("accessDenied", AccessDeniedPage.class);
        this.mountPage("home", ConsultaEmprestavel.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalInit() {
        super.internalInit();

        this.getMarkupSettings().setStripWicketTags(true);
        IResourceSettings resourceSettings = this.getResourceSettings();
        resourceSettings.getResourceFinders().add(0, new WebApplicationPath(this.getServletContext(), "/"));
        resourceSettings.setCachingStrategy(new FilenameWithVersionResourceCachingStrategy(new LastModifiedResourceVersion()));
        
//        Iterator<IRequestMapper> it = ((SystemMapper) this.getRootRequestMapper()).iterator();
//        ImageMapper imageMapper = null;
//        while (it.hasNext()) {
//            IRequestMapper next = it.next();
//            if (next instanceof ImageMapper) {
//                imageMapper = (ImageMapper) next;
//            }
//        }
//        if (imageMapper != null) {
//            ((SystemMapper) this.getRootRequestMapper()).remove(imageMapper);
//        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return ConsultaEmprestavel.class;
    }

    /**
     * @return the securityManager
     */
    @Override
    public SecurityManager<WebSecurityContext> getSecurityManager() {
        return this.securityManager;
    }

    /**
     * @param securityManager the securityManager to set
     */
    @Override
    public void setSecurityManager(SecurityManager<WebSecurityContext> securityManager) {
        this.securityManager = securityManager;
    }

    /**
     * @return the rtmController
     */
    public RtmController getRtmController() {
        return this.rtmController;
    }

    /**
     * @param rtmController the rtmController to set
     */
    public void setRtmController(RtmController rtmController) {
        this.rtmController = rtmController;
    }

    /**
     * @return the authorizationStrategy
     */
    public IAuthorizationStrategy getAuthorizationStrategy() {
        return this.authorizationStrategy;
    }

    /**
     * @param authorizationStrategy the authorizationStrategy to set
     */
    public void setAuthorizationStrategy(IAuthorizationStrategy authorizationStrategy) {
        this.authorizationStrategy = authorizationStrategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Session newSession(Request request, Response response) {
        SecureSession session = new SecureSession(request);
        session.setSecurityManager(this.securityManager);
        if (!StringUtils.isEmpty(this.style)) {
            session.setStyle(this.style);
        }

        return session;
    }

    @Override
    public void setStyle(String style) {
        this.style = style;
    }
}
