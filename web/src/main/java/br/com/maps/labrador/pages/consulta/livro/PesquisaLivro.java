package br.com.maps.labrador.pages.consulta.livro;

import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.web.wicket.pages.form.CrudModelPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.maps.labrador.dao.LivroDAO;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.pages.cadastro.livro.CadastroLivro;

/**
 * Tela de pesquisa de livros
 * 
 * @author laercio.duarte
 * @created Aug 29, 2013
 */
public class PesquisaLivro extends CrudModelPage<PesquisaLivroFilter, Livro>  {

	/**
     * {@inheritDoc}
     */
    public Page createNewPage() {
        return new CadastroLivro(new PageParameters(), this);
    }

    /**
     * {@inheritDoc}
     */
    public List<Livro> search(DAOFactory daoFactory) {
        LivroDAO dao = daoFactory.getDAOByClass(LivroDAO.class);
        PesquisaLivroFilter m = this.getModel();
        return dao.findByName(m.getIsbn10(), m.getIsbn13(), m.getTitulo(), m.getAutor(), m.getEditora());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Page createFormPage(Livro entity, FormType formType) {
        return new CadastroLivro(this, new PageParameters(), entity, formType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PesquisaLivroFilter createModel() {
        return new PesquisaLivroFilter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addResultTableColumns(ReportTableBuilder<Livro> table) {
        table.addStringColumn("isbn13", "ISBN 13", "isbn13");
        table.addStringColumn("isbn10", "ISBN 10", "isbn10");
        table.addStringColumn("titulo", "Título", "titulo");
        table.addStringColumn("autor", "Autor", "autor");
        table.addStringColumn("editora", "Editora", "editora");
        table.addStringColumn("status", "Status", "status");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }
}
