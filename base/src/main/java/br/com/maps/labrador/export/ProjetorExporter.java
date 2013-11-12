package br.com.maps.labrador.export;

import jmine.tec.services.api.export.DefaultExportAction;
import br.com.maps.labrador.domain.projetor.Projetor;
import br.com.maps.labrador.service.incluir.ProjetorService;

/**
 * Exporter para {@link ProjetorService}.
 * 
 * @author finx
 * @created Sep 27, 2013
 */
public class ProjetorExporter extends AbstractEmprestavelExporter<Projetor> {

    /**
     * Construtor.
     */
    public ProjetorExporter() {
        super(new ProjetorService(), DefaultExportAction.INCLUIR);
    }

}
