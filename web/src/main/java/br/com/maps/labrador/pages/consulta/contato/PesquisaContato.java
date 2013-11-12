package br.com.maps.labrador.pages.consulta.contato;

import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.security.api.annotation.Secure;
import jmine.tec.security.api.authorization.UrlPermission;
import jmine.tec.web.wicket.pages.form.CrudModelPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.maps.labrador.dao.ContatoDAO;
import br.com.maps.labrador.domain.contato.Contato;
import br.com.maps.labrador.pages.cadastro.contato.CadastroContato;

/**
 * Tela de pesquisa de mochilas
 * 
 * @author laercio.duarte
 * @created Sep 15, 2013
 */
@Secure(id = "URL_LIST_CONTATO", permissionType = UrlPermission.class)
public class PesquisaContato extends CrudModelPage<PesquisaContatoFilter, Contato> {

    /**
     * {@inheritDoc}
     */
    public Page createNewPage() {
        return new CadastroContato(new PageParameters(), this);
    }

    /**
     * {@inheritDoc}
     */
    public List<Contato> search(DAOFactory daoFactory) {
        ContatoDAO dao = daoFactory.getDAOByClass(ContatoDAO.class);
        PesquisaContatoFilter m = this.getModel();
        return dao.findByNomeOrEmail(m.getNome(), m.getEmail());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Page createFormPage(Contato entity, FormType formType) {
        return new CadastroContato(this, new PageParameters(), entity, formType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PesquisaContatoFilter createModel() {
        return new PesquisaContatoFilter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addResultTableColumns(ReportTableBuilder<Contato> table) {
        table.addStringColumn("nome", "Nome", "nome");
        table.addStringColumn("email", "E-mail", "email");
        table.addStringColumn("assunto", "Assunto", "assunto");
        table.addStringColumn("mensagem", "Mensagem", "mensagem");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MessageCreator getHelpTextCreator() {
        return null;
    }
}
