package br.com.maps.labrador.persister.configurer;

import jmine.tec.persist.impl.di.PersisterListenerConfigurer;
import jmine.tec.persist.impl.di.PersisterListeners;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.persister.action.AtualizacaoAutomaticaCwEmprestavel;

/**
 * Define os listeners invocados para uma instância de {@link AbstractEmprestavel}
 * 
 * @author diego.ferreira
 */
public class EmprestavelPersisterListener implements PersisterListenerConfigurer {

    /**
     * {@inheritDoc}
     */
    public void configure(PersisterListeners listeners) {
        listeners.afterInsert(AbstractEmprestavel.class, AtualizacaoAutomaticaCwEmprestavel.class);
    }
}
