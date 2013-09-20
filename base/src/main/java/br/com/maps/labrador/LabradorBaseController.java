package br.com.maps.labrador;

import jmine.tec.persist.api.persister.StatelessPersister;
import jmine.tec.persist.impl.controller.AbstractPersistenceEnabledController;
import br.com.maps.labrador.actor.EmprestimoActor;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Fachada para o componente labrador-base
 * 
 * @author finx
 * @created Sep 6, 2013
 */
public class LabradorBaseController extends AbstractPersistenceEnabledController {

    private StatelessPersister persister;

    public void executarEmprestimo(LabradorUsuario user, AbstractEmprestavel livro) {
        EmprestimoActor actor = new EmprestimoActor(this);
        actor.executarEmprestimo(user, livro);
    }

    public void devolverEmprestimo(Emprestimo emprestimo) {
        EmprestimoActor actor = new EmprestimoActor(this);
        actor.devolverEmprestimo(emprestimo);
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

}
