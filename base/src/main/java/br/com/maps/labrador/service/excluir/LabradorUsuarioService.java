package br.com.maps.labrador.service.excluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço para exclusão de {@link LabradorUsuarioService}
 * 
 * @author diego.ferreira
 * @created Sep 6, 2013
 */
@ServiceImplementor(action = ActionsEnum.EXCLUIR)
public class LabradorUsuarioService {

    private static final String NOME = "Nome";

    private LabradorUsuario labradorUsuario;

    private StatelessPersister<LabradorUsuario> persister;

    /**
     * Execução do serviço de exclusão de {@link LabradorUsuario}
     */
    @Execution
    public void execute() {
        this.persister.remove(this.labradorUsuario);
    }

    /**
     * @param labradorUsuario the labradorUsuario to set
     */
    @Input(fieldName = NOME)
    public void setLabradorUsuario(LabradorUsuario labradorUsuario) {
        this.labradorUsuario = labradorUsuario;
    }

    /**
     * @param persister the persister to set
     */
    @Injected
    public void setPersister(StatelessPersister<LabradorUsuario> persister) {
        this.persister = persister;
    }
}
