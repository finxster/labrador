package br.com.maps.labrador.export;

import jmine.tec.services.api.export.DefaultExportAction;
import br.com.maps.labrador.domain.mochila.Mochila;
import br.com.maps.labrador.service.incluir.LivroService;
import br.com.maps.labrador.service.incluir.MochilaService;

/**
 * Exporter para {@link MochilaService}.
 * 
 * @author finx
 * @created Sep 27, 2013
 */
public class MochilaExporter extends AbstractEmprestavelExporter<Mochila> {

    /**
     * Construtor.
     */
    public MochilaExporter() {
        super(new MochilaService(), DefaultExportAction.INCLUIR);
    }

}
