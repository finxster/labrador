package br.com.maps.labrador.service.executar;

import jmine.tec.di.annotation.Injected;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.ServiceExecutionException;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;
import br.com.maps.labrador.helper.StaticLabradorUserHelper;

/**
 * Serviço que define um usuário padrão para uma determinada execução.
 * 
 * @author finx
 * @created Sep 27, 2013
 */
@ServiceImplementor(action = ActionsEnum.EXECUTAR)
public class LoginService {

    public final static String USUARIO = "Usuário";

    private LabradorUsuario labradorUsuario;

    private StaticLabradorUserHelper userHelper;

    /**
     * Executa o serviço
     */
    @Execution
    public void execute() throws ServiceExecutionException {
        this.userHelper.setLabradorUsuario(this.labradorUsuario);
    }

    /**
     * @param labradorUsuario the labradorUsuario to set
     */
    @Input(fieldName = USUARIO)
    public void setLabradorUsuario(LabradorUsuario labradorUsuario) {
        this.labradorUsuario = labradorUsuario;
    }

    /**
     * @param userHelper the userHelper to set
     */
    @Injected(name = "userHelper")
    public void setUserHelper(StaticLabradorUserHelper userHelper) {
        this.userHelper = userHelper;
    }

}
