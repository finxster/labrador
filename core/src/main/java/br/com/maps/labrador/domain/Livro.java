package br.com.maps.labrador.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import jmine.tec.component.Documentation;
import jmine.tec.persist.impl.annotation.Alias;
import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;

/**
 * Uma entidade que irá representar um Livro em nosso sistema.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */
@Entity
@Alias("LIVRO")
@Table(name = "LIVRO")
@Documentation("TABELA QUE ARMAZENA OS LIVROS DO SISTEMA")
@SequenceGenerator(name = "SEQ_LIVRO", sequenceName = "SEQ_LIVRO")
public class Livro extends PersistableBusinessObject {

    private Long id;

    private String nome;

    private String descricao;

    /**
     * Construtor
     */
    protected Livro() {
        super();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LIVRO")
    @Documentation("ESSA E NOSSA CHAVE PRIMARIA")
    @Column(name = "COD_LIVRO")
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
    @Documentation("NOME DO LIVRO")
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

    /**
     * @return the descricao
     */
    @Documentation("DESCRICAO DO LIVRO")
    @Column(name = "DESCRICAO")
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
