package br.com.maps.labrador.service.validar;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.ServiceExecutionException;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.ServiceImplementor;
import jmine.tec.services.impl.AbstractValidationService;
import jmine.tec.utils.date.Timestamp;
import br.com.maps.labrador.dao.EmprestimoDAO;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.emprestimo.enumx.StatusEmprestimo;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço de validação de um {@link Emprestimo}
 * 
 * @author laercio.duarte
 * @created Sep 20, 2013
 */
@ServiceImplementor(action = ActionsEnum.VALIDAR)
public class EmprestimoService extends AbstractValidationService {

    private static final String LABRADOR_USUARIO = "Tomador";

    private static final String EMPRESTAVEL = "Emprestável";

    private static final String DATA_DEVOLUCAO = "Data Devolução";

    private static final String STATUS = "Status";

    private StatusEmprestimo status;

    private LabradorUsuario tomador;

    private AbstractEmprestavel emprestavel;

    private Timestamp dataDevolucao;

    private EmprestimoDAO dao;

    /**
     * Valida a existencia de um {@link Emprestimo}
     * 
     * @throws ServiceExecutionException
     */
    @Override
    protected void validate() throws ServiceExecutionException {
        Emprestimo emprestimo;
        try {
            emprestimo = this.dao.findByTomadorEmprestavel(this.tomador, this.emprestavel);
        } catch (BeanNotFoundException e) {
            throw new ServiceExecutionException(e.getLocalizedMessageHolder(), e);
        }
        this.assertEquals("Data devolução", this.dataDevolucao, emprestimo.getDataDevolucao());
        this.assertEquals("Status", this.status, emprestimo.getStatus());
    }

    /**
     * @param status the status to set
     */
    @Input(fieldName = STATUS)
    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    /**
     * @param labradorUsuario the labradorUsuario to set
     */
    @Input(fieldName = LABRADOR_USUARIO)
    public void setLabradorUsuario(LabradorUsuario labradorUsuario) {
        this.tomador = labradorUsuario;
    }

    /**
     * @param emprestavel the emprestavel to set
     */
    @Input(fieldName = EMPRESTAVEL)
    public void setEmprestavel(AbstractEmprestavel emprestavel) {
        this.emprestavel = emprestavel;
    }

    /**
     * @param dataDevolucao the dataDevolucao to set
     */
    @Input(fieldName = DATA_DEVOLUCAO)
    public void setDataDevolucao(Timestamp dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * @param dao the dao to set
     */
    @Injected
    public void setDao(EmprestimoDAO dao) {
        this.dao = dao;
    }
}
