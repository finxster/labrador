package br.com.maps.labrador.export;

import jmine.tec.services.api.export.AbstractServiceFillerEntityExporter;
import jmine.tec.services.api.export.ExportAction;
import jmine.tec.services.api.export.ServiceFiller;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;

/**
 * Exporter base para os {@link AbstractEmprestavel}.
 * 
 * @author finx
 * @created Sep 27, 2013
 * @param <T> tipo do {@link AbstractEmprestavel}.
 */
public abstract class AbstractEmprestavelExporter<T extends AbstractEmprestavel> extends AbstractServiceFillerEntityExporter<T> {

    /**
     * Construtor. Recebe um {@link ServiceFiller} e uma ação.
     * 
     * @param serviceFiller o {@link ServiceFiller}.
     * @param action a ação.
     */
    public AbstractEmprestavelExporter(ServiceFiller<T> serviceFiller, ExportAction action) {
        super(serviceFiller, action);
    }

}
