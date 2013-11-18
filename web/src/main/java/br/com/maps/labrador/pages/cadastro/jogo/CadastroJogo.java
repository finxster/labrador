package br.com.maps.labrador.pages.cadastro.jogo;

import static jmine.tec.web.wicket.bootstrap.BootstrapInputWidth.MEDIUM;

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

import br.com.maps.labrador.domain.jogo.Classificacao;
import br.com.maps.labrador.domain.jogo.Console;
import br.com.maps.labrador.domain.jogo.Jogo;

/**
 * Tela de cadastro de jogos.
 * 
 * @author Felipe Toshio
 * @created Aug 28, 2013
 */
@Secure(id = "URL_EDIT_JOGO", permissionType = UrlPermission.class)
public class CadastroJogo extends FormPage<Jogo> {

    @SpringBean
    private DAOFactory daoFactory;

    /**
     * Construtor.
     * 
     * @param sourcePage página de origem
     * @param sourcePageParameters page parameters da pagina de origem
     * @param entity entidade que deve ser associado a página
     * @param formType tipo do formulario da página
     */
    public CadastroJogo(Page sourcePage, PageParameters sourcePageParameters, Jogo entity, FormType formType) {
        super(sourcePage, sourcePageParameters, entity, formType);
    }

    /**
     * Construtor.
     * 
     * @param sourcePageParameters {@link PageParameters}
     */
    public CadastroJogo(PageParameters sourcePageParameters) {
        super(sourcePageParameters);
    }

    /**
     * Construtor.
     * 
     * @param pageParameters {@link PageParameters}
     * @param pageInstance página de origem.
     */
    public CadastroJogo(PageParameters pageParameters, Page pageInstance) {
        super(pageParameters, pageInstance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Component> createFormComponents() {
        List<Component> components = new ArrayList<Component>();
        components.add(ComponentHelper.createLabeledTextField("nome", "Nome", this.getEntity(), true));
        components.add(ComponentHelper.createLabeledField("console", "Console", Console.class, this.getEntity(), false, MEDIUM));
        components.add(ComponentHelper.createLabeledField("classificacao", "Classificação", Classificacao.class, this.getEntity(), false,
                MEDIUM));
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