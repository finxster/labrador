package br.com.maps.labrador.domain.emprestavel.enumx;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import jmine.tec.component.Documentation;
import jmine.tec.persist.impl.annotation.Alias;
import jmine.tec.persist.impl.annotation.Comment;
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;

import org.hibernate.validator.NotNull;

import br.com.maps.labrador.domain.emprestavel.Emprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

@Entity
@Alias("EMPRES")
@Table(name = "EMPRESTAVEL")
@Documentation("TABELA QUE ARMAZENA OS OBJETOS PASSIVEIS DE EMPRESTIMO.")
@SequenceGenerator(name = "SEQ_EMPRES", sequenceName = "SEQ_EMPRES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TP_EMPRESTAVEL", discriminatorType = DiscriminatorType.INTEGER)
@Comment(table = "EMPRESTAVEL", column = "TP_EMPRESTAVEL", value = "INDICA O TIPO DO OBJETO EMPRESTAVO.")
public abstract class AbstractEmprestavel extends PersistableBusinessObject implements Emprestavel {

    private Long id;

    private LabradorUsuario proprietario;

    private StatusEmprestavel status = StatusEmprestavel.DISPONIVEL;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EMPRES")
    @Documentation("CODIGO QUE IDENTIFICA UM OBJETO QUE EH PASSIVEL DE EMPRESTIMO")
    @Column(name = "COD_EMPRESTAVEL")
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
     * @return the status
     */
    @NotNull
    @Column(name = "STATUS", nullable = false)
    @Documentation("STATUS DO EMPRESTIMO DO EMPRESTAVEL.")
    public StatusEmprestavel getStatus() {
        return this.status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusEmprestavel status) {
        this.status = status;
    }

    /**
     * @return the proprietario
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_USUARIO")
    @Documentation("CODIGO DO USUARIO QUE E O PROPRIETARIO DO LIVRO")
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
     * {@inheritDoc}
     */
    public void emprestar() {
        this.status = StatusEmprestavel.EMPRESTADO;
    }

    /**
     * {@inheritDoc}
     */
    public void devolver() {
        this.status = StatusEmprestavel.DISPONIVEL;
    }

}
