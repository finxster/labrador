package br.com.maps.labrador.helper;

import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.DAOFactory;
import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.security.api.SecurityService;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Classe utilitária responsável por recuperar o usuário atual da sessão.
 * 
 * @author finx
 * @created Sep 6, 2013
 */
public class LabradorUserHelper {

    private SecurityService securityService;

    private DAOFactory daoFactory;

    /**
     * Retorna o {@link LabradorUsuario} associado ao usuário logado na sessão.
     * 
     * @return {@link LabradorUsuario} recuperado.
     */
    public LabradorUsuario getCurrentUser() {
        String currentUser = this.securityService.getCurrentUser();
        DAO<LabradorUsuario> dao = daoFactory.getDAOByEntityType(LabradorUsuario.class);
        try {
            return dao.findByNaturalKey(currentUser);
        } catch (BeanNotFoundException e) {
            throw new RuntimeException("Usuário '" + currentUser + "' não encontrado no sistema.", e);
        }
    }

    /**
     * @param securityService the securityService to set
     */
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    /**
     * @param daoFactory the daoFactory to set
     */
    public void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

}
