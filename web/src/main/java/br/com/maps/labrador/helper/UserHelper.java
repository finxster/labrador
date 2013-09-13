package br.com.maps.labrador.helper;

import java.security.Principal;

import javax.security.auth.Subject;

import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.web.wicket.security.SecureSession;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Classe utilitária responsável por recuperar o usuário da sessão.
 * 
 * @author finx
 * @created Sep 6, 2013
 */
public final class UserHelper {

    /**
     * Construtor privado.
     */
    private UserHelper() {
        super();
    }

    /**
     * Retorna o {@link LabradorUsuario} associado ao usuário logado na sessão.
     * 
     * @param daoFactory fábrica responsável por trazer os dao's necessários para a busca.
     * @return {@link LabradorUsuario} recuperado.
     */
    public static LabradorUsuario getUser(DAOFactory daoFactory) {
        Subject subject = SecureSession.getSecureSession().getSubject();
        Principal principal = subject.getPrincipals().iterator().next();
        DAO<LabradorUsuario> dao = daoFactory.getDAOByEntityType(LabradorUsuario.class);
        try {
            return dao.findByNaturalKey(principal.getName());
        } catch (BeanNotFoundException e) {
            throw new RuntimeException("Usuário" + principal.getName() + " não encontrado no sistema.", e);
        }
    }

}
