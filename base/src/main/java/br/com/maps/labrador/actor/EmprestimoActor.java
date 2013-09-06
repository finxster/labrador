package br.com.maps.labrador.actor;

import jmine.tec.component.actor.AbstractActor;
import jmine.tec.persist.api.DAO;
import jmine.tec.persist.api.persister.StatelessPersister;
import br.com.maps.labrador.LabradorBaseController;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.emprestimo.enumx.StatusEmprestimo;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.domain.livro.enumx.StatusLivro;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Actor responsável por executar as regras de negócio de um {@link Emprestimo}.
 * 
 * @author finx
 * @created Sep 6, 2013
 */
public class EmprestimoActor extends AbstractActor {

    private StatelessPersister persister;

    private LabradorBaseController controller;

    public EmprestimoActor(LabradorBaseController labradorBaseController) {
        this.controller = labradorBaseController;
        this.persister = controller.getPersister();
    }

    public void executarEmprestimo(LabradorUsuario usuario, Livro livro) {
        DAO<Emprestimo> dao = controller.getDAOFactory().getDAOByEntityType(Emprestimo.class);
        Emprestimo emprestimo = dao.createBean();
        emprestimo.setTomador(usuario);
        emprestimo.setLivro(livro);
        persister.save(emprestimo);
    }

    public void devolverEmprestimo(Emprestimo emprestimo) {
        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
        this.persister.save(emprestimo);

        Livro livro = emprestimo.getLivro();
        livro.setStatus(StatusLivro.DISPONIVEL);
        this.persister.save(livro);
    }

}
