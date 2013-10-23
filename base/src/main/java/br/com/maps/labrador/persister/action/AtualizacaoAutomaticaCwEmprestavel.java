package br.com.maps.labrador.persister.action;

import java.util.Set;

import jmine.tec.component.exception.BusinessException;
import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.rtm.api.monitor.Action;
import jmine.tec.security.impl.dao.ChineseWallEntityIdDAO;
import jmine.tec.security.impl.domain.ChineseWallCredential;
import jmine.tec.security.impl.domain.ChineseWallEntityId;
import jmine.tec.security.impl.domain.Credential;
import jmine.tec.security.impl.domain.Group;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * {@link Action} que força a associação automática de um {@link AbstractEmprestavel} a todas as {@link Credential} associadas ao
 * {@link Group} do usuário que efetuou o cadastro.
 * 
 * @author diego.ferreira
 */
public class AtualizacaoAutomaticaCwEmprestavel implements jmine.tec.component.Action<AbstractEmprestavel> {

    @Injected
    private DAOFactory daoFactory;

    public void act(AbstractEmprestavel emprestavel) {
        LabradorUsuario proprietario = emprestavel.getProprietario();

        ChineseWallEntityIdDAO dao = this.daoFactory.getDAOByClass(ChineseWallEntityIdDAO.class);

        DAO<LabradorUsuario> userDAO = this.daoFactory.getDAOByEntityType(LabradorUsuario.class);
        LabradorUsuario userWithSession;
        try {
            userWithSession = userDAO.findByPk(proprietario.getPk());
        } catch (BeanNotFoundException e) {
            throw new BusinessException(e.getLocalizedMessageHolder());
        }

        Set<Group> groups = userWithSession.getUser().getGroups();
        for (Group group : groups) {
            Set<Credential> credentials = group.getCredentials();
            for (Credential credential : credentials) {
                if (credential instanceof ChineseWallCredential) {
                    ChineseWallCredential cwCredential = (ChineseWallCredential) credential;
                    ChineseWallEntityId cwEntityId = dao.createBean();
                    cwEntityId.setEntityId(emprestavel.getId());
                    cwEntityId.setCredential(cwCredential);
                    cwCredential.getIds().add(cwEntityId);
                    cwCredential.getPurePersister().save();
                }
            }
        }
    }
}
