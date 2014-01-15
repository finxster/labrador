package br.com.maps.labrador.pages.cadastro.contato;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.security.api.annotation.Secure;
import jmine.tec.security.api.authorization.UrlPermission;
import jmine.tec.web.wicket.ComponentHelper;
import jmine.tec.web.wicket.pages.form.FormPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.domain.contato.Contato;

/**
 * Tela para cadastro de contato
 * 
 * @author laercio.duarte
 * @created Nov 7, 2013
 */
@Secure(id = "URL_EDIT_CONTATO", permissionType = UrlPermission.class)
public class CadastroContato extends FormPage<Contato> {

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
    public CadastroContato(Page sourcePage, PageParameters sourcePageParameters, Contato entity, FormType formType) {
        super(sourcePage, sourcePageParameters, entity, formType);
    }

    /**
     * Construtor.
     * 
     * @param sourcePageParameters {@link PageParameters}
     */
    public CadastroContato(PageParameters sourcePageParameters) {
        super(sourcePageParameters);
    }

    /**
     * Construtor.
     * 
     * @param pageParameters {@link PageParameters}
     * @param pageInstance página de origem.
     */
    public CadastroContato(PageParameters pageParameters, Page pageInstance) {
        super(pageParameters, pageInstance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Component> createFormComponents() {
        List<Component> components = new ArrayList<Component>();

        components.add(ComponentHelper.createLabeledTextField("nome", "Nome", this.getEntity(), true));
        components.add(ComponentHelper.createLabeledTextField("email", "E-mail", this.getEntity(), true));
        components.add(ComponentHelper.createLabeledTextField("mensagem", "Mensagem", this.getEntity(), true));

        return components;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }

}
