package br.com.maps.labrador.domain.usuario;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import jmine.tec.component.Documentation;
import jmine.tec.persist.impl.annotation.Alias;
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;
import jmine.tec.security.impl.domain.User;

import org.hibernate.validator.NotNull;

import br.com.maps.labrador.domain.livro.Livro;

/**
 * Define um usuário no sistema e sua coleção de {@link Livro}.
 * 
 * @author diego.ferreira
 * @created Sep 6, 2013
 */
@Entity
@Table(name = "LABRADOR_USUARIO")
@Alias(value = "LAUSR")
@SequenceGenerator(name = "SEQ_LAUSR", sequenceName = "SEQ_LAUSR")
@Documentation("REPRESENTA UM USUARIO CADASTRADO NO SISTEMA")
public class LabradorUsuario extends PersistableBusinessObject {

    private Long id;

    private User user;

    private String email;

    private List<Livro> livros;

    /**
     * Construtor.
     */
    protected LabradorUsuario() {
        super();
    }

    /**
     * @return the id
     */
    @Id
    @Documentation("CODIGO DO USUARIO")
    @GeneratedValue(generator = "SEQ_LAUSR", strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
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
     * @return the user
     */
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_USER", nullable = false)
    public User getUser() {
        return this.user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the email
     */
    @NotNull
    @Documentation("EMAIL DO USUARIO ")
    @Column(name = "EMAIL", nullable = false)
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
     * @return the livros
     */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    public List<Livro> getLivros() {
        return this.livros;
    }

    /**
     * @param livros the livros to set
     */
    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    /**
     * @return usuário
     */
    @Transient
    public String getUserName() {
        return this.getUser().getUsername();
    }

    /**
     * @return password
     */
    @Transient
    public String getPassword() {
        return this.getUser().getPassword();
    }
}
