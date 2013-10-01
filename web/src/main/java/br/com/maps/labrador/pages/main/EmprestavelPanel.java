package br.com.maps.labrador.pages.main;

import jmine.tec.web.wicket.pages.main.Home;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;

import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;

/**
 * Painel base para um {@link AbstractEmprestavel}. Mostra a imagem ao lado, e espera receber o texto do lado da imagem.
 * 
 * @author finx
 * @created Oct 1, 2013
 */
public abstract class EmprestavelPanel<T extends AbstractEmprestavel> extends Panel {

    private T emprestavel;

    public EmprestavelPanel(String id, T emprestavel) {
        super(id);
        this.emprestavel = emprestavel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onInitialize() {
        super.onInitialize();

        this.add(new Image("imagem", new PackageResourceReference(Home.class, "Image2.gif")));
        this.add(new Label("resumo", Model.of("Adicionado por " + this.emprestavel.getProprietario().getNome())));
        this.add(this.getPainelEspecifico(this.emprestavel));
    }

    protected abstract Component getPainelEspecifico(T emprestavel);

}
