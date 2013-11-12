package br.com.maps.labrador.service.validar;

import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.ServiceExecutionException;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.ServiceImplementor;
import jmine.tec.services.impl.AbstractValidationService;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;

/**
 * Serviço que valida o status de um empréstimo.
 * 
 * @author finx
 * @created Sep 27, 2013
 */
@ServiceImplementor(action = ActionsEnum.VALIDAR)
public class StatusEmprestimoService extends AbstractValidationService {

    private static final String COISA = "Coisa";

    private static final String STATUS = "Status";

    private AbstractEmprestavel coisa;

    private StatusEmprestavel status;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validate() throws ServiceExecutionException {
        assertEquals(STATUS, status, coisa.getStatus());
    }

    /**
     * @param coisa the coisa to set
     */
    @Input(fieldName = COISA)
    public void setCoisa(AbstractEmprestavel coisa) {
        this.coisa = coisa;
    }

    /**
     * @param status the status to set
     */
    @Input(fieldName = STATUS)
    public void setStatus(StatusEmprestavel status) {
        this.status = status;
    }

}
