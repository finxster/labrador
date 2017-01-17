package br.com.labrador.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Book.
 */
@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "isbn_10")
    private String isbn10;

    @Column(name = "isbn_13")
    private String isbn13;

    @Column(name = "author")
    private String author;

    @Column(name = "p_company")
    private String pCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public Book isbn10(String isbn10) {
        this.isbn10 = isbn10;
        return this;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public Book isbn13(String isbn13) {
        this.isbn13 = isbn13;
        return this;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getAuthor() {
        return author;
    }

    public Book author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getpCompany() {
        return pCompany;
    }

    public Book pCompany(String pCompany) {
        this.pCompany = pCompany;
        return this;
    }

    public void setpCompany(String pCompany) {
        this.pCompany = pCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        if (book.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", isbn10='" + isbn10 + "'" +
            ", isbn13='" + isbn13 + "'" +
            ", author='" + author + "'" +
            ", pCompany='" + pCompany + "'" +
            '}';
    }
}
