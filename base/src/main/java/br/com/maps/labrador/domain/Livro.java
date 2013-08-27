package br.com.maps.labrador.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import jmine.tec.component.Documentation;
import jmine.tec.persist.api.persister.annotation.NaturalKey;
import jmine.tec.persist.impl.annotation.Alias;
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;

/**
 * Uma entidade que irá representar um Livro em nosso sistema.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */
@Entity
@Alias("LIVRO")
@Table(name = "LIVRO")
@Documentation("TABELA QUE ARMAZENA OS LIVROS DO SISTEMA")
@SequenceGenerator(name = "SEQ_LIVRO", sequenceName = "SEQ_LIVRO")
public class Livro extends PersistableBusinessObject {

    private Long id;

    private String isbn;

    private String titulo;

    private String autor;

    private String editora;

    /**
     * Construtor
     */
    protected Livro() {
        super();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LIVRO")
    @Documentation("ESSA E NOSSA CHAVE PRIMARIA")
    @Column(name = "COD_LIVRO")
    public Long getId() {
        return id;
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
    @NaturalKey
    @Documentation("TITULO DO LIVRO")
    @Column(name = "TITULO")
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param nome the nome to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the isbn
     */
    @Documentation("ISBN DO LIVRO")
    @Column(name = "ISBN")
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the autor
     */
    @Documentation("AUTOR DO LIVRO")
    @Column(name = "AUTOR")
    public String getAutor() {
        return autor;
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
        return editora;
    }

    /**
     * @param editora the editora to set
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }
}
