package br.com.maps.labrador.export;

import jmine.tec.services.api.export.DefaultExportAction;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.service.incluir.LivroService;

/**
 * Exporter para {@link LivroService}.
 * 
 * @author finx
 * @created Sep 27, 2013
 */
public class LivroExporter extends AbstractEmprestavelExporter<Livro> {

    /**
     * Construtor.
     */
    public LivroExporter() {
        super(new LivroService(), DefaultExportAction.INCLUIR);
    }

}
