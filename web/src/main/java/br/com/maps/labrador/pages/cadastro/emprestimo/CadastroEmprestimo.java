package br.com.maps.labrador.pages.cadastro.emprestimo;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.web.wicket.ComponentHelper;
import jmine.tec.web.wicket.pages.form.FormPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.maps.labrador.domain.Emprestimo;
import br.com.maps.labrador.domain.Livro;
import br.com.maps.labrador.pages.consulta.emprestimo.PesquisaEmprestimo;

/**
 * Tela que cadastra empréstimos.
 * 
 * @author finx
 * @created Aug 26, 2013
 */
public class CadastroEmprestimo extends FormPage<Emprestimo> {

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
     * {@inheritDoc}
     */
    @Override
    protected List<Component> createFormComponents() {
        List<Component> components = new ArrayList<Component>();
        components.add(ComponentHelper.createLabeledField("livro", "Livro", Livro.class, this.getEntity(), true));
        components.add(ComponentHelper.createLabeledDateField("dataDevolucao", "Data de Devolução", this.getEntity(), false));
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
