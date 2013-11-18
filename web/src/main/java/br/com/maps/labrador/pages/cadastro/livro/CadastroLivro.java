package br.com.maps.labrador.pages.cadastro.livro;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.security.api.annotation.Secure;
import jmine.tec.security.api.authorization.UrlPermission;
import jmine.tec.web.wicket.ComponentHelper;
import jmine.tec.web.wicket.behavior.OnBlurAjaxBehavior;
import jmine.tec.web.wicket.bootstrap.BootstrapInputWidth;
import jmine.tec.web.wicket.bootstrap.behavior.addon.BootstrapAddonBehavior;
import jmine.tec.web.wicket.component.injection.composite.LabeledFormInputPanel;
import jmine.tec.web.wicket.pages.form.FormPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.LabradorWebException;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.helper.IsbnDBHelper;

/**
 * Tela que cadastra livros.
 * 
 * @author diego.ferreira
 * @created Aug 28, 2013
 */
@Secure(id = "URL_EDIT_LIVRO", permissionType = UrlPermission.class)
public class CadastroLivro extends FormPage<Livro> {

    @SpringBean(name = "daoFactory")
    private DAOFactory daoFactory;

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
        final LabeledFormInputPanel isbnTextField = ComponentHelper.createLabeledTextField("isbn10", "ISBN 10", this.getEntity());
        final LabeledFormInputPanel isbn13 = ComponentHelper.createLabeledTextField("isbn13", "ISBN 13", this.getEntity());
        final LabeledFormInputPanel nome = ComponentHelper.createLabeledTextField("nome", "Nome", this.getEntity(), true);
        final LabeledFormInputPanel autor = ComponentHelper.createLabeledTextField("autor", "Autor", this.getEntity());
        final LabeledFormInputPanel editora = ComponentHelper.createLabeledTextField("editora", "Editora", this.getEntity());
        final LabeledFormInputPanel localizacaoTextField =
                ComponentHelper.createLabeledField("localizacao", "Localização", String.class, new PropertyModel<String>(this.getEntity()
                        .getLocalizacao(), "nome"), true, BootstrapInputWidth.MEDIUM);

        isbnTextField.getFormComponent().add(new BootstrapAddonBehavior().setAddon("ISBNdb.com"));
        isbnTextField.add(new OnBlurAjaxBehavior() {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                try {
                    CadastroLivro.this.parseJsonObject(isbnTextField);
                    target.add(isbn13);
                    target.add(nome);
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
        components.add(nome.setOutputMarkupId(true));
        components.add(autor.setOutputMarkupId(true));
        components.add(editora.setOutputMarkupId(true));
        components.add(localizacaoTextField);
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
     * {@inheritDoc}
     */
    @Override
    protected Livro createEntity() {
        Livro livro = super.createEntity();
        DAO<LocalizacaoEmprestavel> dao = this.daoFactory.getDAOByEntityType(LocalizacaoEmprestavel.class);
        LocalizacaoEmprestavel localizacao = dao.createBean();
        livro.setLocalizacao(localizacao);
        return livro;
    }

    /**
     * Obtém as informações do livro cadastrados no isbndb a partir do valor (ISBN10) do {@link TextField} informado.
     * 
     * @param isbnTextField {@link TextField}
     */
    private void parseJsonObject(LabeledFormInputPanel<String> isbnTextField) {
        String modelObject = isbnTextField.getModelObject();
        if (modelObject != null) {
            String isbn10 = modelObject.toUpperCase();
            IsbnDBHelper.getLivroByISBN10(isbn10, this.getEntity());
        }
    }

}
