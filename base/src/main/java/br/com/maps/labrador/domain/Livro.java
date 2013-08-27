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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * Uma entidade que irá representar um Livro em nosso sistema.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */
@Entity
@Alias("LIVRO")
@Table(name = "LIVRO")
@JsonRootName("data")
@JsonIgnoreProperties(ignoreUnknown = true)
@Documentation("TABELA QUE ARMAZENA OS LIVROS DO SISTEMA")
@SequenceGenerator(name = "SEQ_LIVRO", sequenceName = "SEQ_LIVRO")
public class Livro extends PersistableBusinessObject {

    private Long id;

    private String isbn;

    private String title;

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
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    @NaturalKey
    @Documentation("TITULO DO LIVRO")
    @Column(name = "TITULO")
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title the title to set
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the isbn
     */
    @Documentation("ISBN DO LIVRO")
    @Column(name = "ISBN")
    public String getIsbn() {
        return this.isbn;
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
