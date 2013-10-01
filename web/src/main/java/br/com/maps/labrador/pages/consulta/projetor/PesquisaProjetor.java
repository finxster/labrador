package br.com.maps.labrador.pages.consulta.projetor;

import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.web.wicket.pages.form.CrudModelPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.maps.labrador.dao.ProjetorDAO;
import br.com.maps.labrador.domain.projetor.Projetor;
import br.com.maps.labrador.pages.cadastro.projetor.CadastroProjetor;

/**
 * Tela de pesquisa de projetores
 * 
 * @author laercio.duarte
 * @created Sep 15, 2013
 */

public class PesquisaProjetor extends CrudModelPage<PesquisaProjetorFilter, Projetor> {

    /**
     * {@inheritDoc}
     */
    public Page createNewPage() {
        return new CadastroProjetor(new PageParameters(), this);
    }

    /**
     * {@inheritDoc}
     */
    public List<Projetor> search(DAOFactory daoFactory) {
        ProjetorDAO dao = daoFactory.getDAOByClass(ProjetorDAO.class);
        PesquisaProjetorFilter m = this.getModel();
        return dao.findByNameLocalizacao(m.getNome(), m.getLocalizacao());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Page createFormPage(Projetor entity, FormType formType) {
        return new CadastroProjetor(this, new PageParameters(), entity, formType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PesquisaProjetorFilter createModel() {
        return new PesquisaProjetorFilter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addResultTableColumns(ReportTableBuilder<Projetor> table) {
        table.addStringColumn("user", "Usuário", "proprietario.nome");
        table.addStringColumn("nome", "Nome", "nome");
        table.addStringColumn("localizacao", "Localização", "localizacao.nome");
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
