package br.com.maps.labrador.pages.cadastro.projetor;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.web.wicket.ComponentHelper;
import jmine.tec.web.wicket.component.injection.composite.LabeledFormInputPanel;
import jmine.tec.web.wicket.pages.form.FormPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.projetor.Projetor;
import br.com.maps.labrador.helper.LabradorUserHelper;

/**
 * Tela para cadastro de projetores
 * 
 * @author laercio.duarte
 */

public class CadastroProjetor extends FormPage<Projetor> {

    @SpringBean(name = "daoFactory")
    private DAOFactory daoFactory;
    
    @SpringBean
    private LabradorUserHelper userHelper;

    private String localizacao;

    /**
     * Construtor.
     * 
     * @param sourcePage página de origem
     * @param sourcePageParameters page parameters da pagina de origem
     * @param entity entidade que deve ser associado a página
     * @param formType tipo do formulario da página
     */
    public CadastroProjetor(Page sourcePage, PageParameters sourcePageParameters, Projetor entity, FormType formType) {
        super(sourcePage, sourcePageParameters, entity, formType);
    }

    /**
     * Construtor.
     * 
     * @param sourcePageParameters {@link PageParameters}
     */
    public CadastroProjetor(PageParameters sourcePageParameters) {
        super(sourcePageParameters);
    }

    /**
     * Construtor.
     * 
     * @param pageParameters {@link PageParameters}
     * @param pageInstance página de origem.
     */
    public CadastroProjetor(PageParameters pageParameters, Page pageInstance) {
        super(pageParameters, pageInstance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Component> createFormComponents() {
        List<Component> components = new ArrayList<Component>();

        final LabeledFormInputPanel nome = ComponentHelper.createLabeledTextField("nome", "Nome", this.getEntity(), true);
        final LabeledFormInputPanel localizacaoTextField = ComponentHelper.createLabeledTextField("localizacao", "Localização", this, true);

        components.add(nome.setOutputMarkupId(true));
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
    protected boolean beforeSave(Projetor target) {
        // XXX (finx:20130906) isso deveria estar em um persister listener, não na tela!
        DAO<LocalizacaoEmprestavel> dao = this.daoFactory.getDAOByEntityType(LocalizacaoEmprestavel.class);
        LocalizacaoEmprestavel localizacaoProjetor = dao.createBean();
        localizacaoProjetor.setNome(this.localizacao);

        target.setLocalizacao(localizacaoProjetor);

        return super.beforeSave(target);
    }

    /**
     * @return the localizacao
     */
    public String getLocalizacao() {
        return this.localizacao;
    }

    /**
     * @param localizacao the localizacao to set
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

}
