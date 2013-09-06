package br.com.maps.labrador.pages.consulta.emprestimo;

import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.web.wicket.pages.form.CrudModelPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.maps.labrador.dao.EmprestimoDAO;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.pages.cadastro.emprestimo.CadastroEmprestimo;

/**
 * Tela de pesquisa de Empréstimos.
 * 
 * @author finx
 * @created Aug 27, 2013
 */
public class PesquisaEmprestimo extends CrudModelPage<PesquisaEmprestimoFilter, Emprestimo> {

    /**
     * {@inheritDoc}
     */
    public Page createNewPage() {
        return new CadastroEmprestimo(new PageParameters(), this);
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
        return new CadastroEmprestimo(this, new PageParameters(), entity, formType);
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
        table.addStringColumn("livro", "Livro", "livro.titulo");
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
