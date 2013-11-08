package br.com.maps.labrador;

import jmine.tec.persist.impl.controller.AbstractPersistenceEnabledController;
import br.com.maps.labrador.mail.actor.EnvioEmailActor;

/**
 * Fachada para o componente labrador-base
 * 
 * @author fabio.sakiyama
 * @created Nov 07, 2013
 */
public class LabradorMailController extends AbstractPersistenceEnabledController {

    private EnvioEmailActor envioEmailActor;

    /**
     * @return the envioEmailActor
     */
    public EnvioEmailActor getEnvioEmailActor() {
        return this.envioEmailActor;
    }

    /**
     * @param envioEmailActor the envioEmailActor to set
     */
    public void setEnvioEmailActor(EnvioEmailActor envioEmailActor) {
        this.envioEmailActor = envioEmailActor;
    }

}
