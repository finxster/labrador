package br.com.maps.labrador.domain.livro;

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
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.NotNull;

import br.com.maps.labrador.domain.livro.enumx.StatusLivro;
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
@Alias("LIVRO")
@Table(name = "LIVRO")
@JsonIgnoreProperties(ignoreUnknown = true)
@Documentation("TABELA QUE ARMAZENA OS LIVROS DO SISTEMA")
@SequenceGenerator(name = "SEQ_LIVRO", sequenceName = "SEQ_LIVRO")
public class Livro extends PersistableBusinessObject {

    private Long id;

    private String isbn10;

    private String isbn13;

    private String titulo;

    private String autor;

    private String editora;

    private LabradorUsuario usuario;

    private StatusLivro status = StatusLivro.DISPONIVEL;

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
     * @return the titulo
     */
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
     * @return the usuario
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_USUARIO")
    @Documentation("CODIGO DO USUARIO QUE E O PROPRIETARIO DO LIVRO")
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
    @Documentation("STATUS DO EMPRESTIMO DO LIVRO.")
    public StatusLivro getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusLivro status) {
        this.status = status;
    }
}
