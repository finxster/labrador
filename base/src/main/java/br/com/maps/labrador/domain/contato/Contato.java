package br.com.maps.labrador.domain.contato;

import javax.persistence.Column;
import javax.persistence.Entity;

import jmine.tec.component.Documentation;
import jmine.tec.persist.impl.annotation.DiscriminatorComment;

/**
 * Uma entidade que irá representar um contato em nosso sistema
 * 
 * @author laercio.duarte
 * @created Nov 7, 2013
 */
@Entity
@DiscriminatorComment("CONTATO")
public class Contato {

    private String nome;

    private String email;

    private String assunto;

    private String mensagem;

    /**
     * @return the nome
     */
    @Documentation("NOME DO CONTATO")
    @Column(name = "NOME")
    public String getNome() {
        return this.nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the email
     */
    @Documentation("EMAIL DO CONTATO")
    @Column(name = "EMAIL")
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the assunto
     */
    @Documentation("ASSUNTO DO CONTATO")
    @Column(name = "ASSUNTO")
    public String getAssunto() {
        return this.assunto;
    }

    /**
     * @param assunto the assunto to set
     */
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    /**
     * @return the mensagem
     */
    @Documentation("MENSAGEM DO CONTATO")
    @Column(name = "MENSAGEM")
    public String getMensagem() {
        return this.mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
