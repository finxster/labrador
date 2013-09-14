package br.com.maps.labrador.service.excluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.persist.impl.validator.ValidationException;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.ServiceImplementor;
import jmine.tec.services.api.annotations.Validation;
import br.com.maps.labrador.dao.ProjetorDAO;
import br.com.maps.labrador.domain.projetor.Projetor;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço para exclusão de um {@link Projetor} de um {@link LabradorUsuario}
 * 
 * @author laercio.duarte
 * @created Sep 14, 2013
 */

@ServiceImplementor(action = ActionsEnum.EXCLUIR)
public class ProjetorService {

    private static final String LABRADOR_USUARIO = "Usuário";

    private Projetor projetor;

    private LabradorUsuario labradorUsuario;

    private ProjetorDAO projetorDAO;

    private StatelessPersister<Projetor> persister;

    /**
     * Execução do serviço de exclusão do {@link Projetor} para o {@link LabradorUsuario} informado.
     */
    @Execution
    public void execute() {
        try {
            Projetor projetor = this.projetorDAO.findByProjetorUsuario(this.projetor, this.labradorUsuario);
            this.persister.remove(projetor);
        } catch (BeanNotFoundException e) {
            // Validado!
        }
    }

    /**
     * {@inheritDoc}
     */
    @Validation
    public void validate() {
        try {
            this.projetorDAO.findByProjetorUsuario(this.projetor, this.labradorUsuario);
        } catch (BeanNotFoundException e) {
            throw new ValidationException(e.getLocalizedMessageHolder());
        }
    }

    /**
     * @param dao the dao to set
     */
    @Injected(name = "projetorDAO")
    public void setDao(ProjetorDAO dao) {
        this.projetorDAO = dao;
    }

    /**
     * @param labradorUsuario the labradorUsuario to set
     */
    @Input(fieldName = LABRADOR_USUARIO)
    public void setLabradorUsuario(LabradorUsuario labradorUsuario) {
        this.labradorUsuario = labradorUsuario;
    }

    /**
     * @param persister the persister to set
     */
    @Injected
    public void setPersister(StatelessPersister<Projetor> persister) {
        this.persister = persister;
    }
}
