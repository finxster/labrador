package br.com.maps.labrador.pages.consulta.projetor;

import java.io.Serializable;

import jmine.tec.web.wicket.component.injection.FormInputProvider;
import jmine.tec.web.wicket.component.injection.composite.LabeledFormInput;

/**
 * Filtro para a tela de pesquisa de projetores.
 * 
 * @author laercio.duarte
 * @created Sep 15, 2013
 */

@FormInputProvider
public class PesquisaProjetorFilter implements Serializable {

    private String nome;

    private String localizacao;

    /**
     * @return nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @param nome the nome to set
     */
    @LabeledFormInput(label = "Nome")
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return localizacao
     */
    public String getLocalizacao() {
        return this.localizacao;
    }

    /**
     * @param localizacao the localizacao to set
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
