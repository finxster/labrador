package br.com.maps.labrador.pages.cadastro.emprestimo;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.web.wicket.ComponentHelper;
import jmine.tec.web.wicket.pages.form.FormPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.helper.UserHelper;

/**
 * Tela que cadastra empréstimos.
 * 
 * @author finx
 * @created Aug 26, 2013
 */
public class CadastroEmprestimo extends FormPage<Emprestimo> {

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
    public CadastroEmprestimo(Page sourcePage, PageParameters sourcePageParameters, Emprestimo entity, FormType formType) {
        super(sourcePage, sourcePageParameters, entity, formType);
    }

    /**
     * Construtor.
     * 
     * @param sourcePageParameters {@link PageParameters}
     */
    public CadastroEmprestimo(PageParameters sourcePageParameters) {
        super(sourcePageParameters);
    }

    /**
     * Construtor.
     * 
     * @param pageParameters {@link PageParameters}
     * @param pageInstance página de origem.
     */
    public CadastroEmprestimo(PageParameters pageParameters, Page pageInstance) {
        super(pageParameters, pageInstance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Component> createFormComponents() {
        List<Component> components = new ArrayList<Component>();
        components.add(ComponentHelper.createLabeledField("livro", "Livro", Livro.class, getEntity(), true));
        components.add(ComponentHelper.createLabeledDateField("dataDevolucao", "Data de Devolução", getEntity(), false));
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
    protected boolean beforeSave(Emprestimo target) {
        // XXX (finx:20130906) isso deveria estar em um persister listener, não na tela!
        target.setTomador(UserHelper.getUser(daoFactory));
        return super.beforeSave(target);
    }

}
