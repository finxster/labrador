package br.com.maps.labrador.pages.cadastro.emprestimo.controle;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.web.wicket.ComponentHelper;
import jmine.tec.web.wicket.pages.Template;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.LabradorBaseController;
import br.com.maps.labrador.dao.emprestavel.AbstractEmprestavelDAO;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;
import br.com.maps.labrador.helper.UserHelper;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;

/**
 * Tela para controle de empréstimos.
 * 
 * @author finx
 * @created Sep 20, 2013
 */
public class ControleEmprestimo extends Template {

    @SpringBean(name = "daoFactory")
    private DAOFactory daoFactory;

    @SpringBean(name = "labradorBaseController")
    private LabradorBaseController controller;

    private LabradorUsuario usuarioAEmprestar;

    private Form<Void> form;

    private List<AbstractEmprestavel> operacoes = new ArrayList<AbstractEmprestavel>();

    /**
     * Construtor
     */
    public ControleEmprestimo() {
        super();
        this.doPesquisar();
        this.initForm();
    }

    /**
     * Executa a pesquisa de Operações para Batimento.
     */
    private void doPesquisar() {
        AbstractEmprestavelDAO dao = this.daoFactory.getDAOByClass(AbstractEmprestavelDAO.class);
        this.operacoes = dao.findAllByMyUser(UserHelper.getUser(this.daoFactory));
    }

    /**
     * Inicializa o form
     */
    private void initForm() {
        this.form = new Form<Void>("mainForm");
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        this.form.add(feedbackPanel.setOutputMarkupId(true));

        WebMarkupContainer containerLista = new WebMarkupContainer("containerLista");
        ListView<AbstractEmprestavel> listOperacoes =
                new ListView<AbstractEmprestavel>("operacoes", new PropertyModel<List<AbstractEmprestavel>>(this, "operacoes")) {
                    @Override
                    protected void populateItem(ListItem<AbstractEmprestavel> item) {
                        final AbstractEmprestavel emprestavel = item.getModelObject();
                        item.add(new Label("nome", new PropertyModel<String>(emprestavel, "nome")));
                        item.add(new Label("status", new PropertyModel<StatusEmprestavel>(emprestavel, "status")));

                        WebMarkupContainer painelEmprestimo =
                                new WebMarkupContainer("painelEmprestimo", new Model<AbstractEmprestavel>(emprestavel));
                        painelEmprestimo.setOutputMarkupId(true);

                        WebMarkupContainerModal modal = new WebMarkupContainerModal("modal");
                        Teste teste = new Teste("button", modal);
                        FormComponent<LabradorUsuario> usuario =
                                ComponentHelper.createLabeledField(LabradorUsuario.class, "usuarioAEmprestar",
                                        new PropertyModel<LabradorUsuario>(teste, "usuarioAEmprestar"), "Usuário", false);

                        WebMarkupContainer webMarkupContainer = new WebMarkupContainer("content");
                        webMarkupContainer.add(usuario);
                        modal.add(webMarkupContainer);

                        teste.add(new AttributeModifier("class", "btn btn-primary"));
                        teste.setBody(Model.of("Emprestar"));
                        teste.setObjetoAEmprestar(emprestavel);

                        modal.header(Model.of("Empréstimo"));
                        modal.setUseCloseHandler(true);
                        modal.addButton(teste);

                        BotaoModal botaoModal = new BotaoModal("botao", ControleEmprestimo.this.form, modal);
//                        AjaxSubmitLink link = new AjaxSubmitLink("botao") {
//                        };
//                        modal.addOpenerAttributesTo(link);
                        painelEmprestimo.add(botaoModal);
                        painelEmprestimo.add(modal);

                        item.add(painelEmprestimo);

                    }
                };
        containerLista.add(listOperacoes);
        containerLista.setOutputMarkupId(true);
        this.form.add(containerLista);

        // SubmitLink linkPesquisar = new SubmitLink("pesquisar") {
        // @Override
        // public void onSubmit() {
        // doPesquisar();
        // };
        // };
        // this.form.add(linkPesquisar);
        this.add(this.form);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }

    public class BotaoModal extends AjaxSubmitLink {

        private Modal modal;

        public BotaoModal(String id, Form<?> form, Modal modal) {
            super(id, form);
            this.modal = modal;
        }

        @Override
        protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
            this.modal.appendShowDialogJavaScript(target);
        }
    }

    public class Teste extends AjaxSubmitLink {

        private Modal modal;

        public Teste(String id, Modal modal) {
            super(id);
            this.modal = modal;
        }

        private AbstractEmprestavel objetoAEmprestar;

        private LabradorUsuario usuarioAEmprestar;

        // public Teste(AbstractEmprestavel entity) {
        // this.entity = entity;
        // }

        // /**
        // * {@inheritDoc}
        // */
        // @Override
        // public String getLabel() {
        // return "Emprestar";
        // }
        //
        // @Override
        // public boolean isPrimaryButton() {
        // return true;
        // }

        /**
         * @return the usuarioAEmprestar
         */
        public LabradorUsuario getUsuarioAEmprestar() {
            return usuarioAEmprestar;
        }

        /**
         * @param usuarioAEmprestar the usuarioAEmprestar to set
         */
        public void setUsuarioAEmprestar(LabradorUsuario usuarioAEmprestar) {
            this.usuarioAEmprestar = usuarioAEmprestar;
        }

        /**
         * @return the objetoAEmprestar
         */
        public AbstractEmprestavel getObjetoAEmprestar() {
            return objetoAEmprestar;
        }

        /**
         * @param objetoAEmprestar the objetoAEmprestar to set
         */
        public void setObjetoAEmprestar(AbstractEmprestavel objetoAEmprestar) {
            this.objetoAEmprestar = objetoAEmprestar;
        }

        // @Override
        // protected Page execute() {
        // controller.executarEmprestimo(this.usuarioAEmprestar, this.objetoAEmprestar);
        // return ControleEmprestimo.this;
        // }

        // @Override
        // protected void onAction() throws Exception {
        // this.getParent().getParent().getDefaultModelObject();
        // controller.executarEmprestimo(this.usuarioAEmprestar, this.objetoAEmprestar);
        // }

        @Override
        protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
            controller.executarEmprestimo(this.usuarioAEmprestar, this.objetoAEmprestar, null);
            modal.appendCloseDialogJavaScript(target);
            // XXX (finx:20130923) :(
            this.setResponsePage(ControleEmprestimo.this);
        }

    }

    /**
     * @return the usuarioAEmprestar
     */
    public LabradorUsuario getUsuarioAEmprestar() {
        return usuarioAEmprestar;
    }

    /**
     * @param usuarioAEmprestar the usuarioAEmprestar to set
     */
    public void setUsuarioAEmprestar(LabradorUsuario usuarioAEmprestar) {
        this.usuarioAEmprestar = usuarioAEmprestar;
    }

}
