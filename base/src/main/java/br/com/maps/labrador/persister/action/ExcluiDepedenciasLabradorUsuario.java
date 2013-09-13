package br.com.maps.labrador.persister.action;

import java.util.List;

import jmine.tec.component.Action;
import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.persister.StatelessPersister;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Exclui as dependências de um {@link LabradorUsuario}
 * 
 * @author diego.ferreira
 * @created Sep 6, 2013
 */
public class ExcluiDepedenciasLabradorUsuario implements Action<LabradorUsuario> {

    private StatelessPersister persister;

    /**
     * {@inheritDoc}
     */
    public void act(LabradorUsuario labradorUsuario) {
        this.excluiLivrosUsuario(labradorUsuario);
    }

    /**
     * Efetua a exclusão de todos os {@link Livro} que o usuário possui no sistema.
     * 
     * @param labradorUsuario {@link LabradorUsuario}
     */
    private void excluiLivrosUsuario(LabradorUsuario labradorUsuario) {
        List<Livro> livros = labradorUsuario.getLivros();
        for (Livro livro : livros) {
            this.persister.remove(livro);
        }
    }

    /**
     * @param persister the persister to set
     */
    @Injected
    public void setPersister(StatelessPersister persister) {
        this.persister = persister;
    }
}
