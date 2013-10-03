package br.com.maps.labrador.pages.main;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;

/**
 * Painel utilizado para selecionar qual objeto vai ser cadastrado.
 * 
 * @author finx
 * @created Oct 2, 2013
 */
public class CadastrosPanel extends Panel {

    public CadastrosPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Modal modal = new Modal("modalLivros");
        this.add(modal);
        final ImageButton imageButton = new ImageButton("livros", new PackageResourceReference("images/Livros.png")) {
        };
        imageButton.add(new AjaxEventBehavior("onclick") {

            @Override
            protected void onEvent(AjaxRequestTarget target) {
                modal.appendShowDialogJavaScript(target);
            }
        });
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
