package br.com.maps.labrador.pages.consulta.contato;

import java.io.Serializable;

import jmine.tec.web.wicket.component.injection.FormInputProvider;
import jmine.tec.web.wicket.component.injection.composite.LabeledFormInput;

/**
 * Filtro para a tela de pesquisa de contatos.
 * 
 * @author laercio.duarte
 * @created Nov 12, 2013
 */

@FormInputProvider
public class PesquisaContatoFilter implements Serializable {

    private String nome;

    private String email;

    /**
     * @return the nome
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
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    @LabeledFormInput(label = "E-mail")
    public void setEmail(String email) {
        this.email = email;
    }
}
