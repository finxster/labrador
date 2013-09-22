package br.com.maps.labrador.pages.cadastro.livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.web.wicket.ComponentHelper;
import jmine.tec.web.wicket.behavior.OnBlurAjaxBehavior;
import jmine.tec.web.wicket.bootstrap.behavior.addon.BootstrapAddonBehavior;
import jmine.tec.web.wicket.component.injection.composite.LabeledFormInputPanel;
import jmine.tec.web.wicket.pages.form.FormPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.LabradorWebException;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.helper.IsbnDBHelper;
import br.com.maps.labrador.helper.UserHelper;

/**
 * Tela que cadastra livros.
 * 
 * @author diego.ferreira
 * @created Aug 28, 2013
 */
public class CadastroLivro extends FormPage<Livro> {

    @SpringBean(name = "daoFactory")
    private DAOFactory daoFactory;

    private String localizacao;

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
        final LabeledFormInputPanel isbnTextField = ComponentHelper.createLabeledTextField("isbn10", "ISBN 10", getEntity());
        final LabeledFormInputPanel isbn13 = ComponentHelper.createLabeledTextField("isbn13", "ISBN 13", getEntity());
        final LabeledFormInputPanel nome = ComponentHelper.createLabeledTextField("nome", "Nome", getEntity(), true);
        final LabeledFormInputPanel autor = ComponentHelper.createLabeledTextField("autor", "Autor", getEntity());
        final LabeledFormInputPanel editora = ComponentHelper.createLabeledTextField("editora", "Editora", getEntity());
        final LabeledFormInputPanel localizacaoTextField = ComponentHelper.createLabeledTextField("localizacao", "Localização", this, true);

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

    @Override
    protected boolean beforeSave(Livro target) {
        // XXX (finx:20130906) isso deveria estar em um persister listener, não na tela!
        DAO<LocalizacaoEmprestavel> dao = this.daoFactory.getDAOByEntityType(LocalizacaoEmprestavel.class);
        LocalizacaoEmprestavel localizacaoLivro = dao.createBean();
        localizacaoLivro.setNome(this.localizacao);

        target.setLocalizacao(localizacaoLivro);
        target.setProprietario(UserHelper.getUser(this.daoFactory));

        return super.beforeSave(target);
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
        livro.setNome(this.safeCheck(map.get("title")));

        List autores = (List) map.get("author_data");
        if (autores != null && !autores.isEmpty()) {
            Map<String, Object> autoresMap = (Map<String, Object>) autores.get(0);
            livro.setAutor(this.safeCheck(autoresMap.get("name")));
        }
    }

    /**
     * Obtém as informações do livro cadastrados no isbndb a partir do valor (ISBN10) do {@link TextField} informado.
     * 
     * @param isbnTextField {@link TextField}
     */
    private void parseJsonObject(LabeledFormInputPanel<String> isbnTextField) {
        String isbn10 = isbnTextField.getModelObject().toUpperCase();
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

    /**
     * @return the localizacao
     */
    public String getLocalizacao() {
        return localizacao;
    }

    /**
     * @param localizacao the localizacao to set
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

}
