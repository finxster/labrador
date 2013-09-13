package br.com.maps.labrador.domain.livro;

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
 * Defina a localização de um livro.
 * 
 * @author finx
 * @created Sep 13, 2013
 */
@Entity
@Alias("LOCLIV")
@Table(name = "LOCAL_LIVRO")
@Documentation("TABELA QUE ARMAZENA A LOCALIZACAO DOS LIVROS DO SISTEMA")
@SequenceGenerator(name = "SEQ_LOCLIV", sequenceName = "SEQ_LOCLIV")
public class LocalizacaoLivro extends PersistableBusinessObject {

    private Long id;

    private String nome;

    /**
     * Construtor.
     */
    protected LocalizacaoLivro() {
        super();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LOCLIV")
    @Documentation("CODIGO DA LOCALIZACAO DO LIVRO")
    @Column(name = "COD_LOCAL_LIVRO")
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
     * @return the nome
     */
    @NaturalKey
    @Documentation("NOME DA LOCALIZACAO")
    @Column(name = "NOME")
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

}
