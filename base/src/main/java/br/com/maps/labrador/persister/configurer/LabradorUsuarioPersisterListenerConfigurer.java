package br.com.maps.labrador.persister.configurer;

import jmine.tec.persist.impl.di.PersisterListenerConfigurer;
import jmine.tec.persist.impl.di.PersisterListeners;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;
import br.com.maps.labrador.persister.action.ExcluiDepedenciasLabradorUsuario;

/**
 * {@link PersisterListenerConfigurer} para {@link LabradorUsuario}
 * 
 * @author diego.ferreira
 * @created Sep 6, 2013
 */
public class LabradorUsuarioPersisterListenerConfigurer implements PersisterListenerConfigurer {

    public void configure(PersisterListeners listeners) {
        listeners.doRemoveDependencies(LabradorUsuario.class, ExcluiDepedenciasLabradorUsuario.class);
    }
}
