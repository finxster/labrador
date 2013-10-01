package br.com.maps.labrador.service.executar;

import jmine.tec.di.annotation.Injected;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.ServiceImplementor;
import jmine.tec.utils.date.Timestamp;
import br.com.maps.labrador.LabradorBaseController;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;

@ServiceImplementor(action = ActionsEnum.EXECUTAR)
public class EmprestimoService {

    private static final String COISA = "Coisa";

    private static final String DATA_DEVOLUCAO = "Data Devolução";

    private AbstractEmprestavel coisa;

    private Timestamp dataDevolucao;

    private LabradorBaseController controller;

    /**
     * Executa um empréstimo.
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
    public void setDataDevolucao(Timestamp dataDevolucao) {
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
