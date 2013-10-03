package br.com.maps.labrador.pages.main;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

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

        this.add(new Label("livros", Model.of("Livros!")).setOutputMarkupId(true));
        this.add(new Label("equipamentos", Model.of("Equipamentos!")).setOutputMarkupId(true));
        this.add(new Label("jogos", Model.of("Jogos!")).setOutputMarkupId(true));

    }

}
