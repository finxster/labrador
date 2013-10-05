package br.com.maps.labrador.pages.main;

import jmine.tec.persist.api.persister.StatelessPersister;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;

/**
 * Painel utilizado para selecionar qual objeto vai ser cadastrado.
 * 
 * @author finx
 * @created Oct 2, 2013
 */
public class CadastrosPanel extends Panel {

    @SpringBean(name = "hotPersister")
    private StatelessPersister<AbstractEmprestavel> persister;

    public CadastrosPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainerModal modal = new WebMarkupContainerModal("modalLivros");
        CadastroLivroPanel cadastroLivroPanel = new CadastroLivroPanel("cadastroLivro");
        modal.addContent(cadastroLivroPanel);
        AjaxSubmitLink btnGravar = new AjaxSubmitLink("button", cadastroLivroPanel.getForm()) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                persister.save((AbstractEmprestavel) form.getDefaultModelObject());
                // XXX (finx:20131004) :(
                this.setResponsePage(LabradorMain.class);
            }
        };
        btnGravar.add(new AttributeModifier("class", "btn btn-primary"));
        btnGravar.setBody(Model.of("Gravar"));
        modal.addButton(btnGravar);

        this.add(modal);
        final ImageButton imageButton = new ImageButton("livros", new PackageResourceReference("images/Livros.png"));
        imageButton.add(new AjaxEventBehavior("onclick") {

            @Override
            protected void onEvent(AjaxRequestTarget target) {
                modal.appendShowDialogJavaScript(target);
            }
        });
        // modal.addOpenerAttributesTo(imageButton);
        // imageButton.add(new AjaxEventBehavior("onmouseup") {
        //
        // @Override
        // protected void onEvent(AjaxRequestTarget target) {
        // imageButton.add(AttributeModifier.append("src", "images/Livros_selecionado.png"));
        // }
        // });

        this.add(imageButton);
        this.add(new ImageButton("equipamentos", new PackageResourceReference("images/Equipamentos.png")));
        this.add(new ImageButton("jogos", new PackageResourceReference("images/Videogames.png")));

        // this.add(new Label("livros", Model.of("Livros!")).setOutputMarkupId(true));
        // this.add(new Label("equipamentos", Model.of("Equipamentos!")).setOutputMarkupId(true));
        // this.add(new Label("jogos", Model.of("Jogos!")).setOutputMarkupId(true));

    }
}
