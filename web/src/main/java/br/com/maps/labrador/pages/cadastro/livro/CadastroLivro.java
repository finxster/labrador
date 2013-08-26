package br.com.maps.labrador.pages.cadastro.livro;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.web.wicket.ComponentHelper;
import jmine.tec.web.wicket.pages.form.FormPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.maps.labrador.domain.Livro;

public class CadastroLivro extends FormPage<Livro> {

    public CadastroLivro(Class<? extends Page> source, PageParameters sourcePageParameters) {
        super(source, sourcePageParameters);
    }

    public CadastroLivro(Class<? extends Page> source, PageParameters sourcePageParameters, Livro entity, FormType formType) {
        super(source, sourcePageParameters, entity, formType);
    }

    public CadastroLivro(PageParameters sourcePageParameters) {
        super(sourcePageParameters);
    }

    @Override
    protected List<Component> createFormComponents() {
        List<Component> components = new ArrayList<Component>();
        components.add(ComponentHelper.createTextField("isbn"));
        components.add(ComponentHelper.createTextField("titulo"));
        components.add(ComponentHelper.createTextField("autor"));
        components.add(ComponentHelper.createTextField("editora"));
        return components;
    }

    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }

}
