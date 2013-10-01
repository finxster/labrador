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

    public EmprestavelVO() {
        // TODO Auto-generated constructor stub
    }

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

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param proprietario the proprietario to set
     */
    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * @param localizacao the localizacao to set
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
