package br.com.maps.labrador.pages.cadastro.emprestimo.devolucao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.security.api.annotation.Secure;
import jmine.tec.security.api.authorization.UrlPermission;
import jmine.tec.web.wicket.component.command.button.ButtonCommand;
import jmine.tec.web.wicket.component.command.button.SingleEntityExecutionButton;
import jmine.tec.web.wicket.pages.form.ExecutePage;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.LabradorBaseController;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.pages.cadastro.emprestimo.EmprestivoVO;

/**
 * Tela para devolução de empréstimos.
 * 
 * @author finx
 * @created Sep 20, 2013
 */
@Secure(id = "URL_EDIT_DEVOLUCAO_EMPRESTIMO", permissionType = UrlPermission.class)
public class CadastroDevolucaoEmprestimo extends ExecutePage<EmprestivoVO, Emprestimo> {

    @SpringBean(name = "daoFactory")
    private DAOFactory daoFactory;

    @SpringBean(name = "labradorBaseController")
    private LabradorBaseController controller;

    /**
     * Construtor
     */
    public CadastroDevolucaoEmprestimo() {
        super();
    }

    /**
     * Construtor
     * 
     * @param pageParameters {@link PageParameters}
     */
    public CadastroDevolucaoEmprestimo(PageParameters pageParameters) {
        super(pageParameters);
    }

    public List<Emprestimo> search(DAOFactory daoFactory) {
        DAO<Emprestimo> dao = daoFactory.getDAOByEntityType(Emprestimo.class);
        return dao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected EmprestivoVO createModel() {
        return new EmprestivoVO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addResultTableColumns(ReportTableBuilder<Emprestimo> table) {
        table.addStringColumn("nome", "Nome", "emprestavel.nome");
        table.addStringColumn("proprietario", "Proprietário", "emprestavel.proprietario.nome");
        table.addTimestampColumn("dataEmprestimo", "Data do empréstimo", "data");
        table.addDateColumn("dataDevolucao", "Data da devolução", "dataDevolucao");
        table.addStringColumn("status", "Status", "status");
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
        List<ButtonCommand> pageCommands = super.getPageCommands();

        pageCommands.add(new SingleEntityExecutionButton<Emprestimo>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public String getLabel() {
                return "Devolver empréstimo";
            }

            /**
             * {@inheritDoc}
             */
            @Override
            protected void doExecute(Emprestimo entity) {
                CadastroDevolucaoEmprestimo.this.controller.devolverEmprestimo(entity);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            protected Set<Serializable> getSelected() {
                return CadastroDevolucaoEmprestimo.this.getSelectedItens();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            protected Emprestimo loadEntity(Serializable entityId) throws BeanNotFoundException {
                return CadastroDevolucaoEmprestimo.this.loadEntity(entityId);
            }

        });

        return pageCommands;
    }

}
