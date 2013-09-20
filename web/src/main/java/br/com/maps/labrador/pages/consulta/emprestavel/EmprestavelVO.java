package br.com.maps.labrador.pages.consulta.emprestavel;

import java.io.Serializable;

/**
 * Value Object para Emprestaveis.
 * 
 * @author finx
 * @created Sep 19, 2013
 */
public class EmprestavelVO implements Serializable {

    private String nome;

    private String proprietario;

    private String localizacao;

    public EmprestavelVO(String nome, String proprietario, String localizacao) {
        this.nome = nome;
        this.proprietario = proprietario;
        this.localizacao = localizacao;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the proprietario
     */
    public String getProprietario() {
        return proprietario;
    }

    /**
     * @return the localizacao
     */
    public String getLocalizacao() {
        return localizacao;
    }

}
