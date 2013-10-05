package br.com.maps.labrador.pages.main;

import jmine.tec.persist.api.DAOFactory;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.LabradorBaseController;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.pages.consulta.emprestavel.EmprestavelVO;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

/**
 * Painel base para um {@link AbstractEmprestavel}. Mostra a imagem ao lado, e espera receber o texto do lado da imagem.
 * 
 * @author finx
 * @created Oct 1, 2013
 */
public class EmprestavelPanel extends Panel {

    private EmprestavelVO emprestavel;

    @SpringBean
    private DAOFactory daoFactory;

    @SpringBean
    private LabradorBaseController controller;

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

        BootstrapForm<Void> form = new BootstrapForm<Void>("emprestavelForm");

        form.add(new Image("imagem", new PackageResourceReference("images/Picture.png")));
        form.add(new Label("resumo", Model.of("Adicionado por " + this.emprestavel.getProprietario())));
        form.add(new Label("nome", Model.of(this.emprestavel.getNome())));
        form.add(new Label("localizacao", Model.of(this.emprestavel.getLocalizacao())));
        form.add(new Label("status", Model.of(this.emprestavel.getStatus())));

        AjaxSubmitLink pegarEmprestado = new AjaxSubmitLink("pegarEmprestado", form) {

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                AbstractEmprestavel object = emprestavel.toObject(daoFactory);
                controller.executarEmprestimo(object, null);
                target.add(getParent());
            }

        };
        form.add(pegarEmprestado);
        this.add(form);

    }

    protected Component getPainelEspecifico(EmprestavelVO emprestavel) {
        return null;
    }

}
