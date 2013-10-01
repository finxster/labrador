package br.com.maps.labrador.actor;

import jmine.tec.component.actor.AbstractActor;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.persist.impl.dao.BaseDAOFactory;
import jmine.tec.utils.date.Clock;
import jmine.tec.utils.date.Date;
import br.com.maps.labrador.LabradorBaseController;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.emprestimo.enumx.StatusEmprestimo;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;
import br.com.maps.labrador.helper.LabradorUserHelper;

/**
 * Actor responsável por executar as regras de negócio de um {@link Emprestimo}.
 * 
 * @author finx
 * @created Sep 6, 2013
 */
public class EmprestimoActor extends AbstractActor {

    private LabradorUserHelper userHelper;

    private StatelessPersister persister;

    private Clock clock;

    private BaseDAOFactory daoFactory;

    public void executarEmprestimo(AbstractEmprestavel emprestavel, Date dataDevolucao) {
        DAO<Emprestimo> dao = this.daoFactory.getDAOByEntityType(Emprestimo.class);
        Emprestimo emprestimo = dao.createBean();
        emprestimo.setTomador(this.userHelper.getCurrentUser());
        emprestimo.setEmprestavel(emprestavel);
        emprestimo.setDataDevolucao(dataDevolucao);
        persister.save(emprestimo);

        emprestavel.emprestar();
        persister.save(emprestavel);
    }
    
    public void emprestar(LabradorUsuario usuario, AbstractEmprestavel emprestavel, Date dataDevolucao) {
        DAO<Emprestimo> dao = this.daoFactory.getDAOByEntityType(Emprestimo.class);
        Emprestimo emprestimo = dao.createBean();
        emprestimo.setTomador(usuario);
        emprestimo.setEmprestavel(emprestavel);
        emprestimo.setDataDevolucao(dataDevolucao);
        persister.save(emprestimo);

        emprestavel.emprestar();
        persister.save(emprestavel);
    }

    public void devolverEmprestimo(Emprestimo emprestimo) {
        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
        emprestimo.setDataDevolucao(this.clock.currentDate());
        this.persister.save(emprestimo);

        AbstractEmprestavel emprestavel = emprestimo.getEmprestavel();
        emprestavel.devolver();
        this.persister.save(emprestavel);
    }

    /**
     * @return the userHelper
     */
    public LabradorUserHelper getUserHelper() {
        return userHelper;
    }

    /**
     * @param userHelper the userHelper to set
     */
    public void setUserHelper(LabradorUserHelper userHelper) {
        this.userHelper = userHelper;
    }

    /**
     * @return the persister
     */
    public StatelessPersister getPersister() {
        return persister;
    }

    /**
     * @param persister the persister to set
     */
    public void setPersister(StatelessPersister persister) {
        this.persister = persister;
    }

    /**
     * @return the clock
     */
    public Clock getClock() {
        return clock;
    }

    /**
     * @param clock the clock to set
     */
    public void setClock(Clock clock) {
        this.clock = clock;
    }

    /**
     * @return the daoFactory
     */
    public BaseDAOFactory getDaoFactory() {
        return daoFactory;
    }

    /**
     * @param daoFactory the daoFactory to set
     */
    public void setDaoFactory(BaseDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

}
