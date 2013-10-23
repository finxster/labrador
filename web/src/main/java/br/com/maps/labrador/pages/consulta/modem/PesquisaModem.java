package br.com.maps.labrador.pages.consulta.modem;

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

import br.com.maps.labrador.dao.ModemDAO;
import br.com.maps.labrador.domain.modem.Modem;
import br.com.maps.labrador.pages.cadastro.modem.CadastroModem;

/**
 * Tela de pesquisa de Modems
 * 
 * @author laercio.duarte
 * @created Sep 15, 2013
 */
@Secure(id = "URL_LIST_MODEM", permissionType = UrlPermission.class)
public class PesquisaModem extends CrudModelPage<PesquisaModemFilter, Modem> {

    /**
     * {@inheritDoc}
     */
    public Page createNewPage() {
        return new CadastroModem(new PageParameters(), this);
    }

    /**
     * {@inheritDoc}
     */
    public List<Modem> search(DAOFactory daoFactory) {
        ModemDAO dao = daoFactory.getDAOByClass(ModemDAO.class);
        PesquisaModemFilter m = this.getModel();
        return dao.findByNameLocalizacao(m.getNome(), m.getLocalizacao());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Page createFormPage(Modem entity, FormType formType) {
        return new CadastroModem(this, new PageParameters(), entity, formType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PesquisaModemFilter createModel() {
        return new PesquisaModemFilter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addResultTableColumns(ReportTableBuilder<Modem> table) {
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
