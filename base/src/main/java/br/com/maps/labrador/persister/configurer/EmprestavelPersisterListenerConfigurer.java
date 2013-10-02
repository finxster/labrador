package br.com.maps.labrador.persister.configurer;

import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.persister.action.AtualizacaoAutomaticaCwEmprestavel;
import br.com.maps.labrador.persister.action.DefinirProprietario;
import jmine.tec.persist.impl.di.PersisterListenerConfigurer;
import jmine.tec.persist.impl.di.PersisterListeners;

/**
 * {@link PersisterListenerConfigurer} para um {@link AbstractEmprestavel}.
 * 
 * @author finx
 * @created Sep 27, 2013
 */
public class EmprestavelPersisterListenerConfigurer implements PersisterListenerConfigurer {

    /**
     * {@inheritDoc}
     */
    public void configure(PersisterListeners listeners) {
        listeners.beforeInsert(AbstractEmprestavel.class, DefinirProprietario.class);
        
        listeners.afterInsert(AbstractEmprestavel.class, AtualizacaoAutomaticaCwEmprestavel.class);
    }

}
