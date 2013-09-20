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
import jmine.tec.persist.api.persister.annotation.NaturalKey;
import jmine.tec.persist.impl.annotation.Alias;
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;
import jmine.tec.security.impl.domain.User;

import org.hibernate.validator.NotNull;

import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
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

    private String nome;

    private String email;

    private List<AbstractEmprestavel> emprestaveis;

    /**
     * Construtor.
     */
    public LabradorUsuario() {
        super();
    }

    /**
     * @return the id
     */
    @Id
    @Documentation("CODIGO DO USUARIO")
    @GeneratedValue(generator = "SEQ_LAUSR", strategy = GenerationType.AUTO)
    @Column(name = "COD_USUARIO", updatable = false, nullable = false)
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
     * @return the user
     */
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_USER", nullable = false)
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the nome
     */
    @NotNull
    @NaturalKey
    @Documentation("NOME DO USUARIO.")
    @Column(name = "NOME", nullable = false)
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the email
     */
    @NotNull
    @Documentation("EMAIL DO USUARIO.")
    @Column(name = "EMAIL", nullable = false)
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the emprestaveis
     */
    @OneToMany(mappedBy = "proprietario", fetch = FetchType.LAZY)
    public List<AbstractEmprestavel> getEmprestaveis() {
        return this.emprestaveis;
    }

    /**
     * @param emprestaveis the emprestaveis to set
     */
    public void setEmprestaveis(List<AbstractEmprestavel> emprestaveis) {
        this.emprestaveis = emprestaveis;
    }

    /**
     * @return usuário
     */
    @Transient
    public String getUserName() {
        return getUser().getUsername();
    }

    /**
     * @return password
     */
    @Transient
    public String getPassword() {
        return getUser().getPassword();
    }
}
