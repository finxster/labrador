package br.com.maps.labrador.mail.domain;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import jmine.tec.component.Documentation;
import jmine.tec.persist.impl.annotation.Alias;
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;

import org.hibernate.validator.NotNull;

/**
 * Classe que representa um email para ser enviado.
 * 
 * @author fabio.sakiyama
 * @date Nov 8, 2013
 */
@Entity
@Table(name = "EMAIL")
@Alias("EMAIL")
@Documentation("TABELA QUE GUARDA OS EMAILS A ENVIAR")
@SequenceGenerator(name = "SEQ_EMAIL", sequenceName = "SEQ_EMAIL")
public class Email extends PersistableBusinessObject implements Serializable {

    private Long id;

    private String assunto;

    private String corpo;

    private String destinatarios;

    private File anexo;

    /**
     * Retorna o id do email
     * 
     * @return O id do email
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EMAIL")
    @Documentation("CODIGO DO EMPRESTIMO")
    @Column(name = "COD_EMPRESTIMO")
    public Long getId() {
        return this.id;
    }

    /**
     * Seta o id do email
     * 
     * @param id do email
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o assunto do email
     * 
     * @return o assunto do email
     */
    @NotNull
    @Column(name = "ASSUNTO", nullable = false)
    @Documentation("ASSUNTO")
    public String getAssunto() {
        return this.assunto;
    }

    /**
     * Seta o assunto do email
     * 
     * @param assunto do email
     */
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    /**
     * Retorna o corpo do email
     * 
     * @return o corpo do email
     */
    @NotNull
    @Column(name = "CORPO", nullable = false)
    @Documentation("CORPO")
    public String getCorpo() {
        return this.corpo;
    }

    /**
     * Seta o corpo do email
     * 
     * @param corpo do email
     */
    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    /**
     * Retorna os destinatários do email
     * 
     * @return os destinatários do email
     */
    @NotNull
    @Column(name = "DESTINATARIOS", nullable = false)
    @Documentation("DESTINATARIOS")
    public String getDestinatarios() {
        return this.destinatarios;
    }

    /**
     * Seta os destinatários do email
     * 
     * @param destinatarios do email
     */
    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }

    /**
     * Retorna o anexo do email
     * 
     * @return o anexo do email
     */
    // XXX fabio.sakiyama manter transient ou usar blobs?
    @Transient
    public File getAnexo() {
        return this.anexo;
    }

    /**
     * Seta o anexo do email
     * 
     * @param anexo do email
     */
    public void setAnexo(File anexo) {
        this.anexo = anexo;
    }
}
