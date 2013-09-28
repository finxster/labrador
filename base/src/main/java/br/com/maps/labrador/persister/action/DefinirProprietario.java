package br.com.maps.labrador.persister.action;

import jmine.tec.component.Action;
import jmine.tec.di.annotation.Injected;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.helper.LabradorUserHelper;

/**
 * Define um proprietário para um {@link AbstractEmprestavel}.
 * 
 * @author finx
 * @created Sep 27, 2013
 */
public class DefinirProprietario implements Action<AbstractEmprestavel> {

    @Injected
    private LabradorUserHelper userHelper;

    /**
     * {@inheritDoc}
     */
    public void act(AbstractEmprestavel target) {
        if (target.getProprietario() == null) {
            target.setProprietario(this.userHelper.getCurrentUser());
        }
    }

}
