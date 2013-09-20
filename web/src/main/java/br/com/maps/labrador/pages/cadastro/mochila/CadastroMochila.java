package br.com.maps.labrador.pages.cadastro.mochila;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.web.wicket.ComponentHelper;
import jmine.tec.web.wicket.bootstrap.BootstrapInputWidth;
import jmine.tec.web.wicket.component.injection.composite.LabeledFormInputPanel;
import jmine.tec.web.wicket.pages.form.FormPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.mochila.Mochila;
import br.com.maps.labrador.helper.UserHelper;

/**
 * Tela para cadastro de mochilas
 * 
 * @author laercio.duarte
 */

public class CadastroMochila extends FormPage<Mochila> {

    @SpringBean(name = "daoFactory")
    private DAOFactory daoFactory;

    /**
     * Construtor.
     * 
     * @param sourcePage página de origem
     * @param sourcePageParameters page parameters da pagina de origem
     * @param entity entidade que deve ser associado a página
     * @param formType tipo do formulario da página
     */
    public CadastroMochila(Page sourcePage, PageParameters sourcePageParameters, Mochila entity, FormType formType) {
        super(sourcePage, sourcePageParameters, entity, formType);
    }

    /**
     * Construtor.
     * 
     * @param sourcePageParameters {@link PageParameters}
     */
    public CadastroMochila(PageParameters sourcePageParameters) {
        super(sourcePageParameters);
    }

    /**
     * Construtor.
     * 
     * @param pageParameters {@link PageParameters}
     * @param pageInstance página de origem.
     */
    public CadastroMochila(PageParameters pageParameters, Page pageInstance) {
        super(pageParameters, pageInstance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Component> createFormComponents() {
        List<Component> components = new ArrayList<Component>();

        final LabeledFormInputPanel nome = ComponentHelper.createLabeledTextField("nome", "Nome", this.getEntity(), true);
        final LabeledFormInputPanel localizacaoTextField =
                ComponentHelper.createLabeledField("localizacao", "Localização", String.class, new PropertyModel<String>(this.getEntity()
                        .getLocalizacao(), "nome"), true, BootstrapInputWidth.MEDIUM);

        components.add(nome.setOutputMarkupId(true));
        components.add(localizacaoTextField);

        return components;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Mochila createEntity() {
        Mochila mochila = super.createEntity();
        DAO<LocalizacaoEmprestavel> dao = this.daoFactory.getDAOByEntityType(LocalizacaoEmprestavel.class);
        LocalizacaoEmprestavel localizacao = dao.createBean();
        mochila.setLocalizacao(localizacao);
        return mochila;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }

    @Override
    protected boolean beforeSave(Mochila target) {
        // XXX (finx:20130906) isso deveria estar em um persister listener, não na tela!
        target.setProprietario(UserHelper.getUser(this.daoFactory));

        return super.beforeSave(target);
    }

}
