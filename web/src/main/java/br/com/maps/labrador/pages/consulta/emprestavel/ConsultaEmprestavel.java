package br.com.maps.labrador.pages.consulta.emprestavel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.report.impl.ReportBuilder;
import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.web.wicket.component.button.ActionButton;
import jmine.tec.web.wicket.component.command.button.ButtonCommand;
import jmine.tec.web.wicket.component.command.button.SearchCommand;
import jmine.tec.web.wicket.pages.form.BaseListPage;
import jmine.tec.web.wicket.result.BaseResultPanel;
import jmine.tec.web.wicket.result.providers.BaseResultTableProvider;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.dao.LivroDAO;
import br.com.maps.labrador.dao.MochilaDAO;
import br.com.maps.labrador.dao.ModemDAO;
import br.com.maps.labrador.dao.ProjetorDAO;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;

/**
 * Tela que consulta os objetos emprestáveis do sistema.
 * 
 * @author finx
 * @created Sep 20, 2013
 */
public class ConsultaEmprestavel extends BaseListPage<ConsultaEmprestavelFilter, EmprestavelVO> {

    @SpringBean
    private DAOFactory daoFactory;

    /**
     * {@inheritDoc}
     */
    public List<EmprestavelVO> search(DAOFactory daoFactory) {
        LivroDAO livroDAO = daoFactory.getDAOByClass(LivroDAO.class);
        MochilaDAO mochilaDAO = daoFactory.getDAOByClass(MochilaDAO.class);
        ModemDAO modemDAO = daoFactory.getDAOByClass(ModemDAO.class);
        ProjetorDAO projetorDAO = daoFactory.getDAOByClass(ProjetorDAO.class);

        ConsultaEmprestavelFilter m = this.getModel();
        Set<AbstractEmprestavel> emprestaveis = new HashSet<AbstractEmprestavel>();
        emprestaveis.addAll(livroDAO.findByTitulo(m.getEmprestavel()));
        emprestaveis.addAll(livroDAO.findByProprietario(m.getEmprestavel()));
        emprestaveis.addAll(livroDAO.findByLocalizacao(m.getEmprestavel()));

        emprestaveis.addAll(mochilaDAO.findByLikeNaturalKey(m.getEmprestavel()));
        emprestaveis.addAll(mochilaDAO.findByProprietario(m.getEmprestavel()));
        emprestaveis.addAll(mochilaDAO.findByLocalizacao(m.getEmprestavel()));

        emprestaveis.addAll(modemDAO.findByNome(m.getEmprestavel()));
        emprestaveis.addAll(modemDAO.findByProprietario(m.getEmprestavel()));
        emprestaveis.addAll(modemDAO.findByLocalizacao(m.getEmprestavel()));

        emprestaveis.addAll(projetorDAO.findByNome(m.getEmprestavel()));
        emprestaveis.addAll(projetorDAO.findByProprietario(m.getEmprestavel()));
        emprestaveis.addAll(projetorDAO.findByLocalizacao(m.getEmprestavel()));

        List<EmprestavelVO> vos = new ArrayList<EmprestavelVO>();
        for (AbstractEmprestavel emprestavel : emprestaveis) {
            vos.add(new EmprestavelVO(emprestavel.getNome(), emprestavel.getProprietario().getNome(), emprestavel.getLocalizacao()
                    .getNome(), emprestavel.getStatus().toString()));
        }

        return vos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ConsultaEmprestavelFilter createModel() {
        return new ConsultaEmprestavelFilter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addResultTableColumns(ReportTableBuilder<EmprestavelVO> table) {
        table.addStringColumn("nome", "Nome", "nome");
        table.addStringColumn("proprietario", "Proprietário", "proprietario");
        table.addStringColumn("localizacao", "Localização", "localizacao");
        table.addStringColumn("status", "Status", "status");
        // table.addStringColumn("teste", "", new ComponentValueResolver<EmprestavelVO>() {
        //
        // public Component createComponents(String componentId, EmprestavelVO value) {
        // Panel painelEmprestimo = new Panel(componentId) {
        //
        // };
        // List<ButtonCommand> lista = new ArrayList<ButtonCommand>();
        // lista.add(new Teste(null));
        // ButtonCommandsPanel buttonCommandsPanel = new ButtonCommandsPanel(componentId, lista);
        //
        //
        // // painelEmprestimo.setOutputMarkupId(true);
        // //
        // // WebMarkupContainerModal modal = new WebMarkupContainerModal("modal");
        // // Teste teste = new Teste("button", modal);
        // // FormComponent<LabradorUsuario> usuario =
        // // ComponentHelper.createLabeledField(LabradorUsuario.class, "usuarioAEmprestar", new PropertyModel<LabradorUsuario>(
        // // teste, "usuarioAEmprestar"), "Usuário", false);
        // //
        // // WebMarkupContainer webMarkupContainer = new WebMarkupContainer("content");
        // // webMarkupContainer.add(usuario);
        // // modal.add(webMarkupContainer);
        // //
        // // teste.add(new AttributeModifier("class", "btn btn-primary"));
        // // teste.setBody(Model.of("Emprestar"));
        // // teste.setObjetoAEmprestar(null);
        // //
        // // modal.header(Model.of("Empréstimo"));
        // // modal.setUseCloseHandler(true);
        // // modal.addButton(teste);
        // //
        // // // BotaoModal botaoModal = new BotaoModal("botao", ControleEmprestimo.this.form, modal);
        // AjaxSubmitLink link = new AjaxSubmitLink(componentId) {
        // };
        // // modal.addOpenerAttributesTo(link);
        // painelEmprestimo.add(link);
        // // painelEmprestimo.add(modal);
        // return buttonCommandsPanel;
        // }
        //
        // public Object resolveCellValue(EmprestavelVO rowValue) {
        // return "teste";
        // }
        // });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ButtonCommand> getPageCommands() {
        List<ButtonCommand> commands = new ArrayList<ButtonCommand>();
        commands.add(new SearchCommand(this) {
            @Override
            public boolean isPrimaryButton() {
                return true;
            }
        });
        return commands;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Panel createSearchResultPanel(String id, ReportBuilder reportBuilder) {
        this.applyRenderingParameters(reportBuilder);
        return new BaseResultPanel<EmprestavelVO>(id, reportBuilder.createReport(), new BaseResultTableProvider<EmprestavelVO>(
                this.getEntityClass(), this)) {

            @Override
            protected Component createReportTitle(String id) {
                return new Label(id, this.getSearchHandler().search(ConsultaEmprestavel.this.daoFactory).size()
                        + " resultado(s) encontrado(s) na pesquisa.");
            }

        };
    }

    // public class Teste extends AbstractExecutionButton {
    //
    // private AbstractEmprestavel entity;
    //
    // public Teste(AbstractEmprestavel entity) {
    // this.entity = entity;
    // }
    //
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
    // return false;
    // }
    //
    // @Override
    // protected Page getExecutionResult() {
    // // controller.executarEmprestimo(getUsuarioAEmprestar(), this.entity);
    // return ConsultaEmprestavel.this;
    // }

    // }

    public class Bla extends ActionButton {

        public Bla(String id, IModel<String> model) {
            super(id, model);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onAction() throws Exception {
            this.getParent().getParent().getDefaultModelObject();
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
            // controller.executarEmprestimo(this.usuarioAEmprestar, this.objetoAEmprestar);
            modal.appendCloseDialogJavaScript(target);
            // XXX (finx:20130923) :(
            this.setResponsePage(ConsultaEmprestavel.this);
        }

    }

}
