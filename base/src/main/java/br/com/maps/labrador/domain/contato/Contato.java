package br.com.maps.labrador.domain.contato;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import jmine.tec.component.Documentation;
import jmine.tec.persist.impl.annotation.Alias;
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;

/**
 * Uma entidade que irá representar um contato em nosso sistema
 * 
 * @author laercio.duarte
 * @created Nov 7, 2013
 */
@Entity
@Alias("CONTAT")
@Table(name = "CONTAT")
@Documentation("TABELA QUE ARMAZENA OS CONTATOS DO SISTEMA")
@SequenceGenerator(name = "SEQ_CONTAT", sequenceName = "SEQ_CONTAT")
public class Contato extends PersistableBusinessObject {

    private Long id;

    private String nome;

    private String email;

    private String assunto;

    private String mensagem;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CONTAT")
    @Documentation("CODIGO QUE IDENTIFICA UM OBJETO DO TIPO CONTATO")
    @Column(name = "COD_CONTAT")
    public Long getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

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
