package br.com.maps.labrador.export;

import jmine.tec.services.api.export.DefaultExportAction;
import br.com.maps.labrador.domain.modem.Modem;
import br.com.maps.labrador.service.incluir.ModemService;

/**
 * Exporter para {@link ModemService}.
 * 
 * @author finx
 * @created Sep 27, 2013
 */
public class ModemExporter extends AbstractEmprestavelExporter<Modem> {

    /**
     * Construtor.
     */
    public ModemExporter() {
        super(new ModemService(), DefaultExportAction.INCLUIR);
    }

}
