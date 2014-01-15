package br.com.maps.labrador.pages.main;

import jmine.tec.persist.api.DAOFactory;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.web.wicket.behavior.OnBlurAjaxBehavior;
import jmine.tec.web.wicket.bootstrap.behavior.addon.BootstrapAddonBehavior;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.LabradorWebException;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.helper.IsbnDBHelper;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.ControlGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;

/**
 * Painel utilizado para o cadastro de {@link Livro}.
 * 
 * @author finx
 * @created Oct 4, 2013
 */
public class CadastroLivroPanel extends Panel {

    private BootstrapForm<Void> form;
    
    @SpringBean
    private DAOFactory daoFactory;

     @SpringBean(name = "hotPersister")
     private StatelessPersister<Livro> persister;

    public CadastroLivroPanel(String id) {
        super(id);

        form = new BootstrapForm<Void>("mainForm");
        form.type(FormType.Horizontal);
        this.add(new FeedbackPanel("feedback").setOutputMarkupId(true));

        final Livro livro = this.daoFactory.getDAOByEntityType(Livro.class).createBean();
        LocalizacaoEmprestavel localizacao = this.daoFactory.getDAOByEntityType(LocalizacaoEmprestavel.class).createBean();
        livro.setLocalizacao(localizacao);
        form.setDefaultModel(new CompoundPropertyModel<Livro>(livro));

        ControlGroup controlGroupIsbn = new ControlGroup("ctrlGroupIsbn", Model.of("ISBN"));
        final TextField<String> isbnTextField = new TextField<String>("isbn10");
        controlGroupIsbn.add(isbnTextField);
        form.add(controlGroupIsbn.setOutputMarkupId(true));

        ControlGroup controlGroupNome = new ControlGroup("ctrlGroupNome", Model.of("Nome"));
        final TextField<String> nome = new TextField<String>("nome");
        nome.setRequired(true);
        // nome.add(AttributeModifier.append("class", "error"));
        controlGroupNome.add(nome);
        form.add(controlGroupNome.setOutputMarkupId(true));

        ControlGroup controlGroupAutor = new ControlGroup("ctrlGroupAutor", Model.of("Autor"));
        final TextField<String> autor = new TextField<String>("autor");
        controlGroupAutor.add(autor);
        form.add(controlGroupAutor.setOutputMarkupId(true));

        ControlGroup controlGroupEditora = new ControlGroup("ctrlGroupEditora", Model.of("Editora"));
        final TextField<String> editora = new TextField<String>("editora");
        controlGroupEditora.add(editora);
        form.add(controlGroupEditora.setOutputMarkupId(true));

        ControlGroup controlGroupLocalizacao = new ControlGroup("ctrlGroupLocalizacao", Model.of("Localizacao"));
        final TextField<String> localizacaoTextField =
                new TextField<String>("localizacao", new PropertyModel<String>(livro.getLocalizacao(), "nome"));
        controlGroupLocalizacao.add(localizacaoTextField);
        localizacaoTextField.setRequired(true);
        form.add(controlGroupLocalizacao.setOutputMarkupId(true));

        isbnTextField.add(new BootstrapAddonBehavior().setAddon("ISBNdb.com"));
        isbnTextField.add(new OnBlurAjaxBehavior() {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                try {
                    String modelObject = isbnTextField.getModelObject();
                    if (modelObject != null) {
                        IsbnDBHelper.getLivroByISBN10(modelObject.toUpperCase(), livro);
                    }
                    target.add(nome);
                    target.add(autor);
                    target.add(editora);
                } catch (LabradorWebException e) {
                    Component feedBackMessage = CadastroLivroPanel.this.get("feedback");
                    feedBackMessage.error(e.getLocalizedMessageHolder().getMessage());
                    target.add(feedBackMessage);
                }
            }
        });

        AjaxSubmitLink gravar = new AjaxSubmitLink("gravar", form) {

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                persister.save((Livro) getDefaultModelObject());
                // XXX (finx:20131004) :(
                this.setResponsePage(LabradorMain.class);
            }

        };
        this.add(form);
    }

    public Form<?> getForm() {
        return form;
    }

}
