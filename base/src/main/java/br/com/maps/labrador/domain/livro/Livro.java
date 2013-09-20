package br.com.maps.labrador.domain.livro;

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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.NotNull;

import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.emprestavel.enumx.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Uma entidade que irá representar um Livro em nosso sistema.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */
/**
 * @author diego.ferreira
 * @created Sep 6, 2013
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@DiscriminatorValue("1")
@DiscriminatorComment("LIVRO")
public class Livro extends AbstractEmprestavel {

    private String isbn10;

    private String isbn13;

    private String titulo;

    private String autor;

    private String editora;

    private LocalizacaoEmprestavel localizacao;

    /**
     * Construtor
     */
    protected Livro() {
        super();
    }

    /**
     * @return the titulo
     */
    @NotNull
    @NaturalKey
    @Documentation("TITULO DO LIVRO")
    @Column(name = "TITULO")
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the isbn10
     */
    @Documentation("ISBN10 DO LIVRO")
    @Column(name = "ISBN10")
    public String getIsbn10() {
        return this.isbn10;
    }

    /**
     * @param isbn10 the isbn10 to set
     */
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    /**
     * @return the isbn13
     */
    @Documentation("ISBN10 DO LIVRO")
    @Column(name = "ISBN13")
    public String getIsbn13() {
        return this.isbn13;
    }

    /**
     * @param isbn13 the isbn13 to set
     */
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    /**
     * @return the autor
     */
    @Documentation("AUTOR DO LIVRO")
    @Column(name = "AUTOR")
    public String getAutor() {
        return this.autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the editora
     */
    @Documentation("EDITORA DO LIVRO")
    @Column(name = "EDITORA")
    public String getEditora() {
        return this.editora;
    }

    /**
     * @param editora the editora to set
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * @return the localizacao
     */
    @NotNull
    @Index(suffix = "0")
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_LOCAL_LIVRO")
    @Documentation("CODIGO DA LOCALIZACAO DO LIVRO.")
    public LocalizacaoEmprestavel getLocalizacao() {
        return this.localizacao;
    }

    /**
     * @param localizacao the localizacao to set
     */
    public void setLocalizacao(LocalizacaoEmprestavel localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * {@inheritDoc}
     */
    @Transient
    public String getNome() {
        return this.titulo;
    }

}
