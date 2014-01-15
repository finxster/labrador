package br.com.maps.labrador.pages.consulta.logacesso;

import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.security.api.annotation.Secure;
import jmine.tec.security.api.authorization.UrlPermission;
import jmine.tec.security.impl.dao.UserAccessDataDAO;
import jmine.tec.security.impl.domain.UserAccessData;
import jmine.tec.web.wicket.pages.form.CrudModelPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Page;

/**
 * Tela para consulta de logs de acesso
 * 
 * @author laercio.duarte
 * @created Jan 15, 2014
 */
@Secure(id = "URL_LIST_USERACCESSDATA", permissionType = UrlPermission.class)
public class ConsultaUserAccessData extends CrudModelPage<ConsultaUserAccessDataFilter, UserAccessData> {

    /**
     * {@inheritDoc}
     */
    public List<UserAccessData> search(DAOFactory daoFactory) {
        UserAccessDataDAO dao = daoFactory.getDAOByClass(UserAccessDataDAO.class);
        return dao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ConsultaUserAccessDataFilter createModel() {
        return new ConsultaUserAccessDataFilter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addResultTableColumns(ReportTableBuilder<UserAccessData> table) {
        table.addStringColumn("username", "Nome de usuário", "username");
        table.addStringColumn("loginTimestamp", "Login", "loginTimestamp");
        table.addStringColumn("logoutTimestamp", "Logout", "logoutTimestamp");

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
    public Page createNewPage() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Page createFormPage(UserAccessData entity, FormType formType) {
        return null;
    }
}
