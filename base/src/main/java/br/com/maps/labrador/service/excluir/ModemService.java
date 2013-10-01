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
import br.com.maps.labrador.dao.ModemDAO;
import br.com.maps.labrador.domain.modem.Modem;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço para exclusão de um {@link Modem} de um {@link LabradorUsuario}
 * 
 * @author laercio.duarte
 * @created Sep 14, 2013
 */

@ServiceImplementor(action = ActionsEnum.EXCLUIR)
public class ModemService {

    private static final String LABRADOR_USUARIO = "Usuário";

    private Modem modem;

    private LabradorUsuario labradorUsuario;

    private ModemDAO modemDAO;

    private StatelessPersister<Modem> persister;

    /**
     * Execução do serviço de exclusão do {@link Modem} para o {@link LabradorUsuario} informado.
     */
    @Execution
    public void execute() {
        try {
            Modem modem = this.modemDAO.findByModemUsuario(this.modem, this.labradorUsuario);
            this.persister.remove(modem);
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
            this.modemDAO.findByModemUsuario(this.modem, this.labradorUsuario);
        } catch (BeanNotFoundException e) {
            throw new ValidationException(e.getLocalizedMessageHolder());
        }
    }

    /**
     * @param dao the dao to set
     */
    @Injected(name = "modemDAO")
    public void setDao(ModemDAO dao) {
        this.modemDAO = dao;
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
    public void setPersister(StatelessPersister<Modem> persister) {
        this.persister = persister;
    }
}
