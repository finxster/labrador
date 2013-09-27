package br.com.maps.labrador.service.incluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.ServiceImplementor;
import jmine.tec.utils.date.Date;
import br.com.maps.labrador.LabradorBaseController;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço para inclusão de {@link Emprestimo}
 * 
 * @author diego.ferreira
 * @created Sep 6, 2013
 */
@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class EmprestimoService {

    private static final String COISA = "Coisa";

    private static final String DATA_DEVOLUCAO = "Data Devolução";

    private AbstractEmprestavel coisa;

    private Date dataDevolucao;

    private LabradorBaseController controller;

    /**
     * Execução do serviço de cadastro de {@link Emprestimo}
     * 
     * @return {@link Emprestimo}
     */
    @Execution
    public void execute() {
        this.controller.executarEmprestimo(this.coisa, this.dataDevolucao);
    }

    /**
     * @param coisa the coisa to set
     */
    @Input(fieldName = COISA)
    public void setCoisa(AbstractEmprestavel coisa) {
        this.coisa = coisa;
    }

    /**
     * @param dataDevolucao the dataDevolucao to set
     */
    @Input(fieldName = DATA_DEVOLUCAO)
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * @param controller the controller to set
     */
    @Injected
    public void setController(LabradorBaseController controller) {
        this.controller = controller;
    }

}
