package br.com.maps.labrador.pages.consulta.coisas;

import java.io.Serializable;

import jmine.tec.web.wicket.bootstrap.BootstrapInputWidth;
import jmine.tec.web.wicket.component.injection.FormInputProvider;
import jmine.tec.web.wicket.component.injection.composite.LabeledFormInput;

/**
 * Filtro para a tela de consulta de coisas.
 * 
 * @author finx
 * @created Sep 13, 2013
 */
@FormInputProvider
public class ConsultaCoisasFilter implements Serializable {

    private String coisa;

    /**
     * @return the coisa
     */
    public String getCoisa() {
        return coisa;
    }

    /**
     * @param coisa the coisa to set
     */
    @LabeledFormInput(label = "Pesquisar", width = BootstrapInputWidth.XXLARGE)
    public void setCoisa(String coisa) {
        this.coisa = coisa;
    }

}
