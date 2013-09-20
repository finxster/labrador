package br.com.maps.labrador.domain.mochila;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import jmine.tec.component.Documentation;
import jmine.tec.persist.api.persister.annotation.NaturalKey;
import jmine.tec.persist.impl.annotation.DiscriminatorComment;
import jmine.tec.persist.impl.annotation.Index;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.NotNull;

import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.emprestavel.enumx.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Uma entidade que irá representar uma Mochila em nosso sistema.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */
@Entity
@DiscriminatorValue("2")
@DiscriminatorComment("MOCHILA")
public class Mochila extends AbstractEmprestavel {

    private String nome;

    private LocalizacaoEmprestavel localizacao;

    /**
     * Construtor
     */
    protected Mochila() {
        super();
    }

    /**
     * @return the nome da mochila
     */
    @NotNull
    @NaturalKey
    @Documentation("NOME DA MOCHILA")
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
     * @return the localizacao
     */
    @NotNull
    @Index(suffix = "1")
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
