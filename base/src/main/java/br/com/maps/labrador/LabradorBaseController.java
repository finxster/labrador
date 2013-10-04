package br.com.maps.labrador;

import jmine.tec.persist.impl.controller.AbstractPersistenceEnabledController;
import jmine.tec.utils.date.Timestamp;
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

    private EmprestimoActor emprestimoActor;

    public void executarEmprestimo(AbstractEmprestavel livro, Timestamp dataDevolucao) {
        this.emprestimoActor.executarEmprestimo(livro, dataDevolucao);
    }

    public void devolverEmprestimo(Emprestimo emprestimo) {
        this.emprestimoActor.devolverEmprestimo(emprestimo);
    }

    public void emprestar(LabradorUsuario usuario, AbstractEmprestavel emprestavel, Timestamp dataDevolucao) {
        this.emprestimoActor.emprestar(usuario, emprestavel, dataDevolucao);
    }

    /**
     * @return the emprestimoActor
     */
    public EmprestimoActor getEmprestimoActor() {
        return this.emprestimoActor;
    }

    /**
     * @param emprestimoActor the emprestimoActor to set
     */
    public void setEmprestimoActor(EmprestimoActor emprestimoActor) {
        this.emprestimoActor = emprestimoActor;
    }

}
