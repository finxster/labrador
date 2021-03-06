package br.com.maps.labrador.pages.consulta.emprestimo;

import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.security.api.annotation.Secure;
import jmine.tec.security.api.authorization.UrlPermission;
import jmine.tec.web.wicket.pages.form.CrudModelPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Page;

import br.com.maps.labrador.dao.EmprestimoDAO;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;

/**
 * Tela de pesquisa de Empréstimos.
 * 
 * @author finx
 * @created Aug 27, 2013
 */
@Secure(id = "URL_LIST_EMPRESTIMO", permissionType = UrlPermission.class)
public class PesquisaEmprestimo extends CrudModelPage<PesquisaEmprestimoFilter, Emprestimo> {

    /**
     * {@inheritDoc}
     */
    public Page createNewPage() {
        return new PesquisaEmprestimo();
    }

    /**
     * {@inheritDoc}
     */
    public List<Emprestimo> search(DAOFactory daoFactory) {
        EmprestimoDAO dao = daoFactory.getDAOByClass(EmprestimoDAO.class);
        PesquisaEmprestimoFilter m = this.getModel();
        return dao.findByLivro(m.getLivro());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Page createFormPage(Emprestimo entity, FormType formType) {
        return new PesquisaEmprestimo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PesquisaEmprestimoFilter createModel() {
        return new PesquisaEmprestimoFilter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addResultTableColumns(ReportTableBuilder<Emprestimo> table) {
        table.addStringColumn("tomador", "Tomador", "tomador.nome");
        table.addStringColumn("livro", "Livro", "emprestavel.nome");
        table.addStringColumn("donoLivro", "Dono do livro", "emprestavel.proprietario.nome");
        table.addStringColumn("data", "Data", "data");
        table.addStringColumn("dataDevolucao", "Data da devolução", "dataDevolucao");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }

}
