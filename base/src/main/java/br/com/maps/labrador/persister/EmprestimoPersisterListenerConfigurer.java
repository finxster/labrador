package br.com.maps.labrador.persister;

import jmine.tec.persist.impl.di.PersisterListenerConfigurer;
import jmine.tec.persist.impl.di.PersisterListeners;
import br.com.maps.labrador.domain.Emprestimo;

/**
 * {@link PersisterListenerConfigurer} para {@link Emprestimo}.
 * 
 * @author finx
 * @created Aug 26, 2013
 */
public class EmprestimoPersisterListenerConfigurer implements PersisterListenerConfigurer {

    /**
     * {@inheritDoc}
     */
    public void configure(PersisterListeners listeners) {
        listeners.beforeInsert(Emprestimo.class, AtualizaDataEmprestimo.class);
        listeners.beforeInsert(Emprestimo.class, AtualizaStatusLivro.class);
    }

}
