package br.com.maps.labrador.pages.cadastro.emprestimo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.web.wicket.component.command.button.ButtonCommand;
import jmine.tec.web.wicket.component.command.button.SingleEntityExecutionButton;
import jmine.tec.web.wicket.pages.form.ExecutePage;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.LabradorBaseController;
import br.com.maps.labrador.dao.emprestavel.AbstractEmprestavelDAO;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;
import br.com.maps.labrador.helper.UserHelper;

/**
 * Tela que cadastra empréstimos.
 * 
 * @author finx
 * @created Aug 26, 2013
 */
public class CadastroEmprestimo extends ExecutePage<EmprestivoVO, AbstractEmprestavel> {

    @SpringBean(name = "daoFactory")
    private DAOFactory daoFactory;

    @SpringBean(name = "labradorBaseController")
    private LabradorBaseController controller;

    /**
     * Construtor
     */
    public CadastroEmprestimo() {
        super();
    }

    /**
     * Construtor
     * 
     * @param pageParameters {@link PageParameters}
     */
    public CadastroEmprestimo(PageParameters pageParameters) {
        super(pageParameters);
    }

    public List<AbstractEmprestavel> search(DAOFactory daoFactory) {
        AbstractEmprestavelDAO dao = daoFactory.getDAOByClass(AbstractEmprestavelDAO.class);
        return dao.findAllByNotMyUser(UserHelper.getUser(daoFactory));
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
    protected void addResultTableColumns(ReportTableBuilder<AbstractEmprestavel> table) {
        table.addStringColumn("nome", "Nome", "nome");
        table.addStringColumn("proprietario", "Proprietário", "proprietario.nome");
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

        final LabradorUsuario user = UserHelper.getUser(daoFactory);

        pageCommands.add(new SingleEntityExecutionButton<AbstractEmprestavel>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public String getLabel() {
                return "Tomar emprestado";
            }

            /**
             * {@inheritDoc}
             */
            @Override
            protected void doExecute(AbstractEmprestavel entity) {
                controller.executarEmprestimo(user, entity);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            protected Set<Serializable> getSelected() {
                return CadastroEmprestimo.this.getSelectedItens();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            protected AbstractEmprestavel loadEntity(Serializable entityId) throws BeanNotFoundException {
                return CadastroEmprestimo.this.loadEntity(entityId);
            }

        });

        return pageCommands;
    }
}
