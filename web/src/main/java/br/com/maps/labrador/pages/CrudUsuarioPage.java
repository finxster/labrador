package br.com.maps.labrador.pages;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.component.exception.MessageCreator;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.security.api.annotation.Secure;
import jmine.tec.security.api.authorization.UrlPermission;
import jmine.tec.security.impl.dao.UserDAO;
import jmine.tec.security.impl.domain.User;
import jmine.tec.web.wicket.component.command.img.EditCommand;
import jmine.tec.web.wicket.component.command.img.ImageCommand;
import jmine.tec.web.wicket.component.command.img.ViewCommand;
import jmine.tec.web.wicket.pages.form.CrudModelPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.maps.labrador.HelpMessages;
import br.com.maps.labrador.pages.vo.CrudUsuarioVO;

/**
 * Tela de pesquisa e listagem de usuários cadastrados no sistema.
 * 
 * @author jrenaut
 */
@Secure(id = "URL_CONTROLE_ACESSO", permissionType = UrlPermission.class)
public class CrudUsuarioPage extends CrudModelPage<CrudUsuarioVO, User> {

	@SpringBean
	private UserDAO dao;

	/**
	 * {@inheritDoc}
	 */
	public Page createNewPage() {
		return new NovoUsuarioPage(new PageParameters(), this);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> search(DAOFactory daoFactory) {
		return this.dao.findByLikeNaturalKey(this.getModel().getUsername());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Page createFormPage(User entity, FormType formType) {
		return new NovoUsuarioPage(this, new PageParameters(), entity, formType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CrudUsuarioVO createModel() {
		return new CrudUsuarioVO();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addResultTableColumns(ReportTableBuilder<User> table) {
		table.addStringColumn("username", "Nome de usuario", "username");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<ImageCommand> getTableCommands(User entity) {
		if (entity.getId() == 1L) { // validação simplista para o usuário
									// administrador
			List<ImageCommand> commands = new ArrayList<ImageCommand>();
			commands.add(new ViewCommand(this));
			commands.add(new EditCommand(this));
			return commands;
		}
		return super.getTableCommands(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MessageCreator getHelpTextCreator() {
		return HelpMessages.CONTROLE_ACESSO;
	}

}
