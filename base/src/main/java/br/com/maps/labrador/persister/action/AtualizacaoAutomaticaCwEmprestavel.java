package br.com.maps.labrador.persister.action;

import java.util.Set;

import jmine.tec.di.annotation.Injected;
import jmine.tec.rtm.api.monitor.Action;
import jmine.tec.security.impl.dao.ChineseWallEntityIdDAO;
import jmine.tec.security.impl.domain.ChineseWallCredential;
import jmine.tec.security.impl.domain.ChineseWallEntityId;
import jmine.tec.security.impl.domain.Credential;
import jmine.tec.security.impl.domain.Group;
import jmine.tec.security.impl.domain.User;
import br.com.maps.labrador.LabradorBaseController;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;

/**
 * {@link Action} que força a associação automática de um {@link AbstractEmprestavel} a todas as {@link Credential} associadas ao
 * {@link Group} do usuário que efetuou o cadastro.
 * 
 * @author diego.ferreira
 */
public class AtualizacaoAutomaticaCwEmprestavel implements jmine.tec.component.Action<AbstractEmprestavel> {

    @Injected
    private LabradorBaseController controller;

    public void act(AbstractEmprestavel emprestavel) {
        User user = emprestavel.getProprietario().getUser();

        ChineseWallEntityIdDAO dao = this.controller.getDAOFactory().getDAOByClass(ChineseWallEntityIdDAO.class);

        Set<Group> groups = user.getGroups();
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
