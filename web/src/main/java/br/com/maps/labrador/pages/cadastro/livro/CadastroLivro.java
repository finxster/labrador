package br.com.maps.labrador.pages.cadastro.livro;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.web.wicket.ComponentHelper;
import jmine.tec.web.wicket.behavior.OnBlurAjaxBehavior;
import jmine.tec.web.wicket.pages.form.FormPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import br.com.maps.labrador.LabradorWebException;
import br.com.maps.labrador.LabradorWebMessages;
import br.com.maps.labrador.domain.Livro;
import br.com.maps.labrador.helper.IsbnDBHelper;

/**
 * Tela que cadastra livros.
 * 
 * @author diego.ferreira
 * @created Aug 28, 2013
 */
public class CadastroLivro extends FormPage<Livro> {

    // XXX (diego.ferreira) este parâmetro deverá ser configurado em um ".properties" e injetado via spring
    private static final String MAPS_ISBNDB_COD = "SQGBZAKH";
    
    /**
     * Construtor.
     * 
     * @param sourcePage página de origem
     * @param sourcePageParameters page parameters da pagina de origem
     * @param entity entidade que deve ser associado a página
     * @param formType tipo do formulario da página
     */
    public CadastroLivro(Page sourcePage, PageParameters sourcePageParameters, Livro entity, FormType formType) {
        super(sourcePage, sourcePageParameters, entity, formType);
    }

    /**
     * Construtor.
     * 
     * @param sourcePageParameters {@link PageParameters}
     */
    public CadastroLivro(PageParameters sourcePageParameters) {
        super(sourcePageParameters);
    }

    /**
     * Construtor.
     * 
     * @param pageParameters {@link PageParameters}
     * @param pageInstance página de origem.
     */
    public CadastroLivro(PageParameters pageParameters, Page pageInstance) {
        super(pageParameters, pageInstance);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Component> createFormComponents() {
        List<Component> components = new ArrayList<Component>();
        final TextField<String> isbnTextField = ComponentHelper.createTextField("isbn10");
        final TextField<String> isbn13 = ComponentHelper.createTextField("isbn13");
        final TextField<String> titulo = ComponentHelper.createTextField("titulo");
        final TextField<String> autor = ComponentHelper.createTextField("autor");
        final TextField<String> editora = ComponentHelper.createTextField("editora");

        isbnTextField.add(new OnBlurAjaxBehavior() {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                try {
                    CadastroLivro.this.parseJsonObject(isbnTextField);
                    target.add(isbn13);
                    target.add(titulo);
                    target.add(autor);
                    target.add(editora);
                } catch (LabradorWebException e) {
                    Component feedBackMessage = CadastroLivro.this.get("feedback");
                    feedBackMessage.warn(e.getLocalizedMessageHolder().getMessage());
                    target.add(feedBackMessage);
                }
            }
        });

        components.add(isbnTextField.setOutputMarkupId(true));
        components.add(isbn13.setOutputMarkupId(true));
        components.add(titulo.setOutputMarkupId(true));
        components.add(autor.setOutputMarkupId(true));
        components.add(editora.setOutputMarkupId(true));
        return components;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }

    /**
     * Efetua o parte do {@link Map} com as informações obtidas no isbndb e hidrata a instância de livro informada.
     * 
     * @param mp {@link Map}
     * @param livro {@link Livro}
     */
    private void hidrateEntity(Map<String, Object> mp, Livro livro) {
        Map<String, Object> map = (Map<String, Object>) ((List) mp.get("data")).get(0);
        livro.setIsbn10(this.safeCheck(map.get("isbn10")));
        livro.setIsbn13(this.safeCheck(map.get("isbn13")));
        livro.setEditora(this.safeCheck(map.get("publisher_text")));
        livro.setTitulo(this.safeCheck(map.get("title")));

        List autores = (List) map.get("author_data");
        if (autores != null && !autores.isEmpty()) {
            Map<String, Object> autoresMap = (Map<String, Object>) autores.get(0);
            livro.setAutor(this.safeCheck(autoresMap.get("name")));
        }
    }

    /**
     * Obtém as informações do livro cadastrados no isbndb a partir do valor (ISBN10) do {@link TextField} informado.
     * 
     * @param textField {@link TextField}
     */
    private void parseJsonObject(TextField<String> textField) {
        String isbn10 = textField.getModelObject().toUpperCase();
        IsbnDBHelper.getLivroByISBN10(isbn10, getEntity());
    }

    /**
     * Efetua um null check na string informada.
     * 
     * @param str string que será analisada
     * @return a própria string caso a mesma não seja nula.
     */
    private String safeCheck(Object str) {
        if (!StringUtils.isEmpty((String) str)) {
            return str.toString();
        }
        return null;
    }
}
