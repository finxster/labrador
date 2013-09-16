package br.com.maps.labrador.pages.consulta.mochila;

import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.web.wicket.pages.form.CrudModelPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.maps.labrador.dao.MochilaDAO;
import br.com.maps.labrador.domain.mochila.Mochila;
import br.com.maps.labrador.pages.cadastro.mochila.CadastroMochila;

/**
 * Tela de pesquisa de mochilas
 * 
 * @author laercio.duarte
 * @created Sep 15, 2013
 */

public class PesquisaMochila extends CrudModelPage<PesquisaMochilaFilter, Mochila> {

    /**
     * {@inheritDoc}
     */
    public Page createNewPage() {
        return new CadastroMochila(new PageParameters(), this);
    }

    /**
     * {@inheritDoc}
     */
    public List<Mochila> search(DAOFactory daoFactory) {
        MochilaDAO dao = daoFactory.getDAOByClass(MochilaDAO.class);
        PesquisaMochilaFilter m = this.getModel();
        return dao.findByName(m.getNome());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Page createFormPage(Mochila entity, FormType formType) {
        return new CadastroMochila(this, new PageParameters(), entity, formType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PesquisaMochilaFilter createModel() {
        return new PesquisaMochilaFilter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addResultTableColumns(ReportTableBuilder<Mochila> table) {
        table.addStringColumn("user", "Usuário", "usuario.nome");
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
