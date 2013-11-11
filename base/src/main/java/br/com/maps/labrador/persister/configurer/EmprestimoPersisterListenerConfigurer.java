package br.com.maps.labrador.persister.configurer;

import jmine.tec.persist.impl.di.PersisterListenerConfigurer;
import jmine.tec.persist.impl.di.PersisterListeners;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.persister.action.AtualizaDataEmprestimo;

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
    }

}
