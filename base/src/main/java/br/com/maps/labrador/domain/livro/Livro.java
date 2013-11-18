package br.com.maps.labrador.domain.livro;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import jmine.tec.component.Documentation;
import jmine.tec.persist.impl.annotation.DiscriminatorComment;

import org.hibernate.annotations.Filter;

import br.com.maps.labrador.chinese.EmprestavelChineseWallEntity;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;

/**
 * Uma entidade que irá representar um Livro em nosso sistema.
 * 
 * @author diego.ferreira
 * @created Sep 6, 2013
 */
@Entity
@DiscriminatorValue("1")
@DiscriminatorComment("LIVRO")
@Filter(name = EmprestavelChineseWallEntity.FILTER_NAME, condition = EmprestavelChineseWallEntity.CONDITION)
public class Livro extends AbstractEmprestavel {

    private String isbn10;

    private String isbn13;

    private String autor;

    private String editora;

    /**
     * Construtor
     */
    protected Livro() {
        super();
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

}
