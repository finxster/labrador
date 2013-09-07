package br.com.maps.labrador.persister;

import jmine.tec.component.Action;
import jmine.tec.di.annotation.Injected;
import jmine.tec.persist.api.persister.StatelessPersister;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.domain.livro.enumx.StatusLivro;

/**
 * Atualiza o status de empréstimo de um {@link Livro}.
 * 
 * @author finx
 * @created Sep 6, 2013
 */
public class AtualizaStatusLivro implements Action<Emprestimo> {

    @Injected
    private StatelessPersister<Livro> persister;

    /**
     * {@inheritDoc}
     */
    public void act(Emprestimo target) {
        Livro livro = target.getLivro();
        livro.setStatus(StatusLivro.EMPRESTADO);
        this.persister.save(livro);
    }

}
