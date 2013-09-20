package br.com.maps.labrador.actor;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import jmine.tec.persist.api.persister.StatelessPersister;
import junit.framework.Assert;

import org.junit.Test;

import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.emprestimo.enumx.StatusEmprestimo;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Testes para as regras de negócio de {@link Emprestimo}.
 * 
 * @author finx
 * @created Sep 6, 2013
 */
public class EmprestimoActorTest {

    public void testEmprestar() {
    }

//    @Test
    public void testDevolver() {
        LabradorUsuario usuario = mock(LabradorUsuario.class);
        Livro livro = mock(Livro.class);

        Emprestimo emprestimo = mock(Emprestimo.class);
        when(emprestimo.getEmprestavel()).thenReturn(livro);

        StatelessPersister persister = mock(StatelessPersister.class);

        EmprestimoActor actor = new EmprestimoActor(null);

        actor.devolverEmprestimo(emprestimo);

        assertEquals(StatusEmprestimo.DEVOLVIDO, emprestimo.getStatus());
    }

}
