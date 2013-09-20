package br.com.maps.labrador.service.incluir;

import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.services.api.ActionsEnum;
import jmine.tec.services.api.annotations.Execution;
import jmine.tec.services.api.annotations.Input;
import jmine.tec.services.api.annotations.Output;
import jmine.tec.services.api.annotations.ServiceImplementor;
import jmine.tec.utils.date.Clock;
import jmine.tec.utils.date.Date;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Serviço para inclusão de {@link Emprestimo}
 * 
 * @author diego.ferreira
 * @created Sep 6, 2013
 */
@ServiceImplementor(action = ActionsEnum.INCLUIR)
public class EmprestimoService {

    private static final String IDENTIFICADOR = "Identificador";

    private static final String LIVRO = "Livro";

    private static final String DATA_DEVOLUCAO = "Data Devolução";

    private static final String LABRADOR_USUARIO = "Tomador";

    private Clock clock;

    private Livro livro;

    private Date dataDevolucao;

    private DAO<Emprestimo> dao;

    private LabradorUsuario labradorUsuario;

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
        emprestimo.setEmprestavel(this.livro);
        emprestimo.setData(this.clock.currentTimestamp());
        emprestimo.setDataDevolucao(this.dataDevolucao);
        emprestimo.setTomador(this.labradorUsuario);
        this.persister.save(emprestimo);
        return emprestimo;
    }

    /**
     * @param livro the livro to set
     */
    @Input(fieldName = LIVRO)
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    /**
     * @param dataDevolucao the dataDevolucao to set
     */
    @Input(fieldName = DATA_DEVOLUCAO)
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * @param labradorUsuario the labradorUsuario to set
     */
    @Input(fieldName = LABRADOR_USUARIO)
    public void setLabradorUsuario(LabradorUsuario labradorUsuario) {
        this.labradorUsuario = labradorUsuario;
    }

    /**
     * @param clock the clock to set
     */
    @Injected
    public void setClock(Clock clock) {
        this.clock = clock;
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
