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

/**
 * Tela que cadastra empréstimos.
 * 
 * @author finx
 * @created Aug 26, 2013
 */
public class CadastroEmprestimo extends FormPage<Emprestimo> {

    public CadastroEmprestimo(Class<? extends Page> source, PageParameters sourcePageParameters) {
        super(source, sourcePageParameters);
    }

    public CadastroEmprestimo(Class<? extends Page> source, PageParameters sourcePageParameters, Emprestimo entity, FormType formType) {
        super(source, sourcePageParameters, entity, formType);
    }

    public CadastroEmprestimo(PageParameters sourcePageParameters) {
        super(sourcePageParameters);
    }

    @Override
    protected List<Component> createFormComponents() {
        List<Component> components = new ArrayList<Component>();
        components.add(ComponentHelper.createLabeledField("livro", "Livro", Livro.class, this.getEntity(), true));
        components.add(ComponentHelper.createLabeledDateField("dataDevolucao", "Data de Devolução", this.getEntity(), false));
        return components;
    }

    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }

}
