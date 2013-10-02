package br.com.maps.labrador.pages.main;

import jmine.tec.web.wicket.pages.main.Home;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;

import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.pages.consulta.emprestavel.EmprestavelVO;

/**
 * Painel base para um {@link AbstractEmprestavel}. Mostra a imagem ao lado, e espera receber o texto do lado da imagem.
 * 
 * @author finx
 * @created Oct 1, 2013
 */
public class EmprestavelPanel extends Panel {

    private EmprestavelVO emprestavel;

    public EmprestavelPanel(String id, EmprestavelVO emprestavel) {
        super(id);
        this.emprestavel = emprestavel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onInitialize() {
        super.onInitialize();

        this.add(new Image("imagem", new PackageResourceReference(Home.class, "user-default.png")));
        this.add(new Label("resumo", Model.of("Adicionado por " + this.emprestavel.getProprietario())));
        this.add(new Label("nome", Model.of(this.emprestavel.getNome())));
        this.add(new Label("localizacao", Model.of(this.emprestavel.getLocalizacao())));
        this.add(new Label("status", Model.of(this.emprestavel.getStatus())));
    }

    protected Component getPainelEspecifico(EmprestavelVO emprestavel) {
        return null;
    }

}
