package br.com.maps.labrador.export;

import jmine.tec.services.api.export.AbstractServiceFillerEntityExporter;
import jmine.tec.services.api.export.DefaultExportAction;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.service.incluir.EmprestimoService;

/**
 * Exporter para {@link EmprestimoService}.
 * 
 * @author finx
 * @created Sep 27, 2013
 */
public class EmprestimoExporter extends AbstractServiceFillerEntityExporter<Emprestimo> {

    /**
     * Construtor.
     */
    public EmprestimoExporter() {
        super(new EmprestimoService(), DefaultExportAction.INCLUIR);
    }

}
