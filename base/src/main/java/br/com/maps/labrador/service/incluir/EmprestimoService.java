package br.com.maps.labrador.service.incluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.Output;
import jmine.tec.services.api.annotations.ServiceImplementor;
import jmine.tec.services.api.export.ReferenceMap;
import jmine.tec.services.api.export.ServiceFiller;
import jmine.tec.services.api.io.ServiceBean;
import jmine.tec.services.impl.export.impl.ExportUtils;
import jmine.tec.utils.date.Timestamp;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.emprestimo.enumx.StatusEmprestimo;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço para inclusão de {@link Emprestimo}
 * 
 * @author diego.ferreira
 * @created Sep 6, 2013
 */
@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class EmprestimoService implements ServiceFiller<Emprestimo> {

    private static final String IDENTIFICADOR = "Identificador";

    private static final String COISA = "Coisa";

    private static final String DATA = "Data";

    private static final String DATA_DEVOLUCAO = "Data Devolução";

    private static final String TOMADOR = "Tomador";

    private static final String STATUS = "Status";

    private AbstractEmprestavel coisa;

    private Timestamp data;

    private Timestamp dataDevolucao;

    private DAO<Emprestimo> dao;

    private LabradorUsuario tomador;

    private StatusEmprestimo status;

    private StatelessPersister<Emprestimo> persister;

    /**
     * Execução do serviço de cadastro de {@link Emprestimo}
     * 
     * @return {@link Emprestimo}
     */
    @Execution
    @Output(propertyName = IDENTIFICADOR)
    public Emprestimo execute() {
        Emprestimo emprestimo = this.dao.createBean();
        emprestimo.setEmprestavel(this.coisa);
        emprestimo.setData(this.data);
        emprestimo.setDataDevolucao(this.dataDevolucao);
        emprestimo.setTomador(this.tomador);
        emprestimo.setStatus(this.status);
        this.persister.save(emprestimo);
        return emprestimo;
    }

    /**
     * {@inheritDoc}
     */
    public void fillServiceBean(ServiceBean bean, ReferenceMap referenceMap, Emprestimo entity) {
        bean.setAction(ActionsEnum.INCLUIR);
        bean.setName(ExportUtils.getServiceName(EmprestimoService.class));

        ExportUtils.put(bean, IDENTIFICADOR, "emprestimo" + entity.getId());
        ExportUtils.put(bean, COISA, entity.getEmprestavel());
        ExportUtils.put(bean, DATA, entity.getData());
        ExportUtils.put(bean, DATA_DEVOLUCAO, entity.getDataDevolucao() == null ? null : entity.getDataDevolucao().toString());
        ExportUtils.put(bean, TOMADOR, entity.getTomador().getNome());
        ExportUtils.put(bean, STATUS, entity.getStatus());
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
    @Input(fieldName = DATA_DEVOLUCAO, required = false)
    public void setDataDevolucao(Timestamp dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * @param data the data to set
     */
    @Input(fieldName = DATA)
    public void setData(Timestamp data) {
        this.data = data;
    }

    /**
     * @param tomador the tomador to set
     */
    @Input(fieldName = TOMADOR)
    public void setTomador(LabradorUsuario tomador) {
        this.tomador = tomador;
    }

    /**
     * @param status the status to set
     */
    @Input(fieldName = STATUS)
    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    /**
     * @param dao the dao to set
     */
    @Injected
    public void setDao(DAO<Emprestimo> dao) {
        this.dao = dao;
    }

    /**
     * @param persister the persister to set
     */
    @Injected
    public void setPersister(StatelessPersister<Emprestimo> persister) {
        this.persister = persister;
    }
}
