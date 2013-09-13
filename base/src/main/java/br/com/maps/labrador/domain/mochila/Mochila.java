package br.com.maps.labrador.domain.mochila;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import jmine.tec.component.Documentation;
import jmine.tec.persist.api.persister.annotation.NaturalKey;
import jmine.tec.persist.impl.annotation.Alias;
import jmine.tec.persist.impl.annotation.Index;
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.NotNull;

import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Uma entidade que irá representar uma Mochila em nosso sistema.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */

@Entity
@Alias("MOCHILA")
@Table(name = "MOCHILA")
@Documentation("TABELA QUE ARMAZENA AS MOCHILAS DO SISTEMA")
@SequenceGenerator(name = "SEQ_MOCHILA", sequenceName = "SEQ_MOCHILA")
public class Mochila extends PersistableBusinessObject {

    private Long id;

    private String nome;

    private LabradorUsuario usuario;

    private StatusEmprestavel status = StatusEmprestavel.DISPONIVEL;

    private LocalizacaoEmprestavel localizacao;

    /**
     * Construtor
     */
    protected Mochila() {
        super();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_MOCHILA")
    @Documentation("ESSA E NOSSA CHAVE PRIMARIA")
    @Column(name = "COD_MOCHILA")
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
     * @return the nome da mochila
     */
    @NotNull
    @NaturalKey
    @Documentation("NOME DA MOCHILA")
    @Column(name = "NOME", nullable = false)
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
     * @return the usuario
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_USUARIO")
    @Documentation("CODIGO DO USUARIO QUE E O PROPRIETARIO DA MOCHILA")
    public LabradorUsuario getUsuario() {
        return this.usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(LabradorUsuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the status
     */
    @NotNull
    @Column(name = "STATUS", nullable = false)
    @Documentation("STATUS DO EMPRESTIMO DA MOCHILA.")
    public StatusEmprestavel getStatus() {
        return this.status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusEmprestavel status) {
        this.status = status;
    }

    /**
     * @return the localizacao
     */
    @NotNull
    @Index(suffix = "0")
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_LOCAL_MOCHILA")
    @Documentation("CODIGO DA LOCALIZACAO DO MOCHILA.")
    public LocalizacaoEmprestavel getLocalizacao() {
        return this.localizacao;
    }

    /**
     * @param localizacao the localizacao to set
     */
    public void setLocalizacao(LocalizacaoEmprestavel localizacao) {
        this.localizacao = localizacao;
    }

}
