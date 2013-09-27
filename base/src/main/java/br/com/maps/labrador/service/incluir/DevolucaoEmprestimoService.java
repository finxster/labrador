package br.com.maps.labrador.service.incluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.ServiceExecutionException;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.Output;
import jmine.tec.services.api.annotations.ServiceImplementor;
import br.com.maps.labrador.LabradorBaseController;
import br.com.maps.labrador.dao.EmprestimoDAO;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço para {@link CadastroDevolucaoEmprestimo} de {@link Emprestimo}
 * 
 * @author laercio.duarte
 * @created Sep 27, 2013
 */
@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class DevolucaoEmprestimoService {

    private static final String IDENTIFICADOR = "Identificador";

    private static final String COISA = "Coisa";

    private static final String LABRADOR_USUARIO = "Tomador";

    private LabradorBaseController controller;

    private AbstractEmprestavel coisa;

    private EmprestimoDAO dao;

    private LabradorUsuario tomador;

    /**
     * Execução do serviço de cadastro de {@link CadastroDevolucaoEmprestimo}
     * 
     * @return {@link Emprestimo}
     * @throws ServiceExecutionException
     */
    @Execution
    @Output(propertyName = IDENTIFICADOR)
    public Emprestimo execute() throws ServiceExecutionException {
        Emprestimo emprestimo;
        try {
            emprestimo = this.dao.findByTomadorEmprestavel(this.tomador, this.coisa);
        } catch (BeanNotFoundException e) {
            throw new ServiceExecutionException(e.getLocalizedMessageHolder(), e);
        }
        controller.devolverEmprestimo(emprestimo);
        return emprestimo;
    }

    /**
     * @param coisa the coisa to set
     */
    @Input(fieldName = COISA)
    public void setCoisa(AbstractEmprestavel coisa) {
        this.coisa = coisa;
    }

    /**
     * @param labradorUsuario the labradorUsuario to set
     */
    @Input(fieldName = LABRADOR_USUARIO)
    public void setLabradorUsuario(LabradorUsuario labradorUsuario) {
        this.tomador = labradorUsuario;
    }

    /**
     * @param controller the controller to set
     */
    @Injected
    public void setController(LabradorBaseController controller) {
        this.controller = controller;
    }

    /**
     * @param dao the dao to set
     */
    @Injected
    public void setDao(EmprestimoDAO dao) {
        this.dao = dao;
    }

    /**
     * @param tomador the tomador to set
     */
    @Input(fieldName = LABRADOR_USUARIO)
    public void setTomador(LabradorUsuario tomador) {
        this.tomador = tomador;
    }

}
