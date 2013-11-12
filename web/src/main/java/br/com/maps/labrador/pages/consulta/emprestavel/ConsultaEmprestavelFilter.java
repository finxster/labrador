package br.com.maps.labrador.pages.consulta.emprestavel;

import java.io.Serializable;

import jmine.tec.web.wicket.bootstrap.BootstrapInputWidth;
import jmine.tec.web.wicket.component.injection.FormInputProvider;
import jmine.tec.web.wicket.component.injection.composite.LabeledFormInput;

/**
 * Filtro para a tela de consulta de objetos emprestáveis.
 * 
 * @author finx
 * @created Sep 13, 2013
 */
@FormInputProvider
public class ConsultaEmprestavelFilter implements Serializable {

    private String emprestavel;

    /**
     * @return the emprestavel
     */
    public String getEmprestavel() {
        return emprestavel;
    }

    /**
     * @param emprestavel the emprestavel to set
     */
    @LabeledFormInput(label = "Pesquisar", width = BootstrapInputWidth.XXLARGE)
    public void setEmprestavel(String emprestavel) {
        this.emprestavel = emprestavel;
    }

}
