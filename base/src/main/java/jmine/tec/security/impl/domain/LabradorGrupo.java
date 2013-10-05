package jmine.tec.security.impl.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import jmine.tec.component.Documentation;
import jmine.tec.persist.impl.annotation.Alias;
import jmine.tec.persist.impl.annotation.Comment;
import jmine.tec.persist.impl.annotation.Comments;
import jmine.tec.persist.impl.annotation.Index;
import jmine.tec.persist.impl.annotation.Indexes;
import jmine.tec.persist.impl.schema.api.IndexOrder;

import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.NotNull;

import br.com.maps.labrador.domain.usuario.LabradorUsuario;

@Entity
public class LabradorGrupo extends Group {

    private LabradorUsuario proprietario;

    private Set<LabradorUsuario> administradores = new HashSet<LabradorUsuario>();

    /**
     * Construtor.
     */
    protected LabradorGrupo() {
        super();
    }

    /**
     * Construtor
     * 
     * @param name nome do grupo
     */
    public LabradorGrupo(String name) {
        super(name);
    }

    /**
     * @return the proprietario
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_PROPRIETARIO")
    @Documentation("PROPRIETARIO DO GRUPO")
    public LabradorUsuario getProprietario() {
        return this.proprietario;
    }

    /**
     * @param proprietario the proprietario to set
     */
    public void setProprietario(LabradorUsuario proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * @return the administradores
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ASSOC_ADM_GROUP", joinColumns = { @JoinColumn(name = "COD_USER_GROUP") }, inverseJoinColumns = { @JoinColumn(name = "COD_USUARIO") })
    @Alias("ASADGR")
    @org.hibernate.annotations.Fetch(FetchMode.SELECT)
    @Comments({ @Comment(table = "ASSOC_ADM_GROUP", value = "ARMAZENA A ASSOCIACAO GRUPO DE USUARIOS E OS SEUS ADMINISTRADORES."),
            @Comment(table = "ASSOC_ADM_GROUP", column = "COD_USUARIO", value = "CODIGO DA CREDENCIAL."),
            @Comment(table = "ASSOC_ADM_GROUP", column = "COD_USER_GROUP", value = "CODIGO DO GRUPO DE USUARIO.") })
    @Indexes({ @Index(columns = "COD_USER_GROUP", suffix = "0", orders = IndexOrder.ASC),
            @Index(columns = "COD_USUARIO", suffix = "1", orders = IndexOrder.ASC) })
    public Set<LabradorUsuario> getAdministradores() {
        return this.administradores;
    }

    /**
     * @param administradores the administradores to set
     */
    public void setAdministradores(Set<LabradorUsuario> administradores) {
        this.administradores = administradores;
    }

    public void addAdministrador(LabradorUsuario labradorUsuario) {
        this.administradores.add(labradorUsuario);
    }
}
