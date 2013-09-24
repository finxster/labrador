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

    private String status;

    public EmprestavelVO(String nome, String proprietario, String localizacao, String status) {
        this.nome = nome;
        this.proprietario = proprietario;
        this.localizacao = localizacao;
        this.status = status;
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

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

}
