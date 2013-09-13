package br.com.maps.labrador.persister.action;

import jmine.tec.component.Action;
import jmine.tec.di.annotation.Injected;
import jmine.tec.utils.date.Clock;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;

/**
 * Atualiza a data de um emprestimo.
 * 
 * @author finx
 * @created Aug 26, 2013
 */
public class AtualizaDataEmprestimo implements Action<Emprestimo> {

    @Injected
    private Clock clock;

    /**
     * {@inheritDoc}
     */
    public void act(Emprestimo target) {
        target.setData(clock.currentTimestamp());
    }
}
