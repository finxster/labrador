package br.com.maps.labrador.domain.modem;

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
import jmine.tec.persist.impl.annotation.Index;
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.NotNull;

import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * Uma entidade que irá representar um Modem em nosso sistema.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */

@Entity
@Alias("MODEM")
@Table(name = "MODEM")
@Documentation("TABELA QUE ARMAZENA OS MODEMS DO SISTEMA")
@SequenceGenerator(name = "SEQ_MODEM", sequenceName = "SEQ_MODEM")
public class Modem extends PersistableBusinessObject {

    private Long id;

    private String nome;

    private LabradorUsuario usuario;

    private StatusEmprestavel status = StatusEmprestavel.DISPONIVEL;

    private LocalizacaoEmprestavel localizacao;

    /**
     * Construtor
     */
    protected Modem() {
        super();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_MODEM")
    @Documentation("ESSA E NOSSA CHAVE PRIMARIA")
    @Column(name = "COD_MODEM")
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
     * @return the nome
     */
    @NotNull
    @NaturalKey
    @Documentation("NOME DO MODEM")
    @Column(name = "NOME", nullable = false)
    public String getNome() {
        return this.nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the usuario
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_USUARIO")
    @Documentation("CODIGO DO USUARIO QUE E O PROPRIETARIO DO MODEM")
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
    @Documentation("STATUS DO EMPRESTIMO DO MODEM.")
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
     * @return the localizacao
     */
    @NotNull
    @Index(suffix = "0")
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_LOCAL_MODEM")
    @Documentation("CODIGO DA LOCALIZACAO DO MODEM.")
    public LocalizacaoEmprestavel getLocalizacao() {
        return this.localizacao;
    }

    /**
     * @param localizacao the localizacao to set
     */
    public void setLocalizacao(LocalizacaoEmprestavel localizacao) {
        this.localizacao = localizacao;
    }

}
