package br.com.maps.labrador.domain.emprestavel;

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
import jmine.tec.persist.api.persister.annotation.NaturalKey;
import jmine.tec.persist.impl.annotation.Alias;
import jmine.tec.persist.impl.annotation.Comment;
import jmine.tec.persist.impl.annotation.Index;
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Filter;
import org.hibernate.validator.NotNull;

import br.com.maps.labrador.chinese.EmprestavelChineseWallEntity;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

@Entity
@Alias("EMPRES")
@Table(name = "EMPRESTAVEL")
@Documentation("TABELA QUE ARMAZENA OS OBJETOS PASSIVEIS DE EMPRESTIMO.")
@SequenceGenerator(name = "SEQ_EMPRES", sequenceName = "SEQ_EMPRES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TP_EMPRESTAVEL", discriminatorType = DiscriminatorType.INTEGER)
@Comment(table = "EMPRESTAVEL", column = "TP_EMPRESTAVEL", value = "INDICA O TIPO DO OBJETO EMPRESTAVO.")
@Filter(name = EmprestavelChineseWallEntity.FILTER_NAME, condition = EmprestavelChineseWallEntity.CONDITION)
public abstract class AbstractEmprestavel extends PersistableBusinessObject implements Emprestavel {

    private Long id;

    private String nome;

    private LabradorUsuario proprietario;

    private StatusEmprestavel status = StatusEmprestavel.DISPONIVEL;

    private LocalizacaoEmprestavel localizacao;

    /**
     * {@inheritDoc}
     */
    public void devolver() {
        this.status = StatusEmprestavel.DISPONIVEL;
    }

    /**
     * {@inheritDoc}
     */
    public void emprestar() {
        this.status = StatusEmprestavel.EMPRESTADO;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EMPRES")
    @Documentation("CODIGO QUE IDENTIFICA UM OBJETO QUE EH PASSIVEL DE EMPRESTIMO")
    @Column(name = EmprestavelChineseWallEntity.COLUMN_NAME)
    public Long getId() {
        return this.id;
    }

    /**
     * @return the localizacao
     */
    @NotNull
    @Index(suffix = "0")
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_LOCAL_EMPRESTAVEL")
    @Documentation("CODIGO DA LOCALIZACAO DO EMPRESTAVEL.")
    public LocalizacaoEmprestavel getLocalizacao() {
        return this.localizacao;
    }

    /**
     * @return the nome
     */
    @NotNull
    @NaturalKey
    @Column(name = "NOME", nullable = false)
    @Documentation("NOME DO OBJETO QUE EH EMPRESTAVEL.")
    public String getNome() {
        return this.nome;
    }

    /**
     * @return the proprietario
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_USUARIO", nullable = false)
    @Documentation("CODIGO DO USUARIO QUE EH O PROPRIETARIO DO EMPRESTAVEL.")
    public LabradorUsuario getProprietario() {
        return this.proprietario;
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
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param localizacao the localizacao to set
     */
    public void setLocalizacao(LocalizacaoEmprestavel localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param proprietario the proprietario to set
     */
    public void setProprietario(LabradorUsuario proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusEmprestavel status) {
        this.status = status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.nome;
    }

}
