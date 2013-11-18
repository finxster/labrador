package br.com.maps.labrador.domain.emprestavel;

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

/**
 * Define a localização de um objeto emprestável.
 * 
 * @author finx
 * @created Sep 13, 2013
 */
@Entity
@Alias("LOCEMP")
@Table(name = "LOCAL_EMPRESTAVEL")
@Documentation("TABELA QUE ARMAZENA A LOCALIZACAO DOS OBEJTOS EMPRESTAVEIS, NO SENTIDO DE EMPRESTAR, DO SISTEMA")
@SequenceGenerator(name = "SEQ_LOCEMP", sequenceName = "SEQ_LOCEMP")
public class LocalizacaoEmprestavel extends PersistableBusinessObject {

    private Long id;

    private String nome;

    /**
     * Construtor.
     */
    protected LocalizacaoEmprestavel() {
        super();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LOCEMP")
    @Documentation("CODIGO DA LOCALIZACAO DO EMPRESTAVEL")
    @Column(name = "COD_LOCAL_EMPRESTAVEL")
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
    @NaturalKey
    @Documentation("NOME DA LOCALIZACAO")
    @Column(name = "NOME")
    public String getNome() {
        return this.nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

}
