package br.com.maps.labrador.domain.emprestimo;

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
import jmine.tec.persist.impl.annotation.Alias;
import jmine.tec.persist.impl.annotation.Index;
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;
import jmine.tec.utils.date.Date;
import jmine.tec.utils.date.Timestamp;

import org.hibernate.annotations.Type;
import org.hibernate.validator.NotNull;

import br.com.maps.labrador.domain.emprestavel.Emprestavel;
import br.com.maps.labrador.domain.emprestavel.enumx.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestimo.enumx.StatusEmprestimo;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Representa um empréstimo no sistema.
 * 
 * @author finx
 * @created Aug 26, 2013
 */
@Entity
@Alias("EMPRST")
@Table(name = "EMPRESTIMO")
@Documentation("TABELA QUE ARMAZENA OS EMPRESTIMOS REALIZADOS NO SISTEMA")
@SequenceGenerator(name = "SEQ_EMPRST", sequenceName = "SEQ_EMPRST")
public class Emprestimo extends PersistableBusinessObject {

    private Long id;

    private AbstractEmprestavel emprestavel;

    private Timestamp data;

    private Date dataDevolucao;

    private StatusEmprestimo status = StatusEmprestimo.EFETUADO;

    private LabradorUsuario tomador;

    /**
     * Construtor.
     */
    protected Emprestimo() {
        super();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EMPRST")
    @Documentation("CODIGO DO EMPRESTIMO")
    @Column(name = "COD_EMPRESTIMO")
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
     * @return the emprestavel
     */
    @NotNull
    @Index(suffix = "0")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_EMPRESTAVEL")
    @Documentation("CODIGO DO OBJETO EMPRESTAVEL.")
    public AbstractEmprestavel getEmprestavel() {
        return this.emprestavel;
    }

    /**
     * @param emprestavel the emprestavel to set
     */
    public void setEmprestavel(AbstractEmprestavel emprestavel) {
        this.emprestavel = emprestavel;
    }

    /**
     * @return the data
     */
    @NotNull
    @Column(name = "DT", nullable = false)
    @Documentation("DATA DO EMPRESTIMO")
    @Type(type = "jmine.tec.persist.impl.hibernate.type.TimestampType")
    public Timestamp getData() {
        return data;
    }

    /**
     * @param data the dataHora to set
     */
    public void setData(Timestamp data) {
        this.data = data;
    }

    /**
     * @return the dataDevolucao
     */
    @Column(name = "DT_DEVOLUCAO")
    @Documentation("DATA DA DEVOLUCAO DO EMPRESTIMO")
    @Type(type = "jmine.tec.persist.impl.hibernate.type.DateType")
    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    /**
     * @param dataDevolucao the dataDevolucao to set
     */
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * @return the status
     */
    @NotNull
    @Column(name = "STATUS", nullable = false)
    @Documentation("STATUS DO EMPRESTIMO.")
    public StatusEmprestimo getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    /**
     * @return the tomador
     */
    @NotNull
    @Index(suffix = "1")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_TOMADOR")
    @Documentation("CODIGO DO TOMADOR.")
    public LabradorUsuario getTomador() {
        return tomador;
    }

    /**
     * @param tomador the tomador to set
     */
    public void setTomador(LabradorUsuario tomador) {
        this.tomador = tomador;
    }
}
