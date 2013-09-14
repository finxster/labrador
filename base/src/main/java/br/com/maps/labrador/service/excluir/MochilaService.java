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
import br.com.maps.labrador.dao.MochilaDAO;
import br.com.maps.labrador.domain.mochila.Mochila;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço para exclusão de um {@link Mochila} de um {@link LabradorUsuario}
 * 
 * @author laercio.duarte
 * @created Sep 14, 2013
 */

@ServiceImplementor(action = ActionsEnum.EXCLUIR)
public class MochilaService {

    private static final String LABRADOR_USUARIO = "Usuário";

    private Mochila mochila;

    private LabradorUsuario labradorUsuario;

    private MochilaDAO mochilaDAO;

    private StatelessPersister<Mochila> persister;

    /**
     * Execução do serviço de exclusão do {@link Mochila} para o {@link LabradorUsuario} informado.
     */
    @Execution
    public void execute() {
        try {
            Mochila mochila = this.mochilaDAO.findByMochilaUsuario(this.mochila, this.labradorUsuario);
            this.persister.remove(mochila);
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
            this.mochilaDAO.findByMochilaUsuario(this.mochila, this.labradorUsuario);
        } catch (BeanNotFoundException e) {
            throw new ValidationException(e.getLocalizedMessageHolder());
        }
    }

    /**
     * @param dao the dao to set
     */
    @Injected(name = "mochilaDAO")
    public void setDao(MochilaDAO dao) {
        this.mochilaDAO = dao;
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
    public void setPersister(StatelessPersister<Mochila> persister) {
        this.persister = persister;
    }
}
