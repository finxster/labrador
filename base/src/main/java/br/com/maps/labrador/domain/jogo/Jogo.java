package br.com.maps.labrador.domain.jogo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import jmine.tec.component.Documentation;
import jmine.tec.persist.impl.annotation.DiscriminatorComment;

import org.hibernate.annotations.Filter;

import br.com.maps.labrador.chinese.EmprestavelChineseWallEntity;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;

/**
 * Uma entidade representa um Jogo no sistema.
 * 
 * @author Felipe Toshio
 * @created Nov 18, 2013
 */
@Entity
@DiscriminatorValue("5")
@DiscriminatorComment("JOGO")
@Filter(name = EmprestavelChineseWallEntity.FILTER_NAME, condition = EmprestavelChineseWallEntity.CONDITION)
public class Jogo extends AbstractEmprestavel {

    private Console console;

    private Classificacao classificacao;

    private int numeroJogadores;

    /**
     * @return the console
     */
    @Documentation("O CONSOLE DO RESPECTIVO JOGO")
    @Column(name = "CONSOLE")
    public Console getConsole() {
        return this.console;
    }

    /**
     * @param console the console to set
     */
    public void setConsole(Console console) {
        this.console = console;
    }

    /**
     * @return the classificacao
     */
    @Documentation("A CLASSIFICACAO ETARIA DO JOGO")
    @Column(name = "CLASSIF_JOGO")
    public Classificacao getClassificacao() {
        return this.classificacao;
    }

    /**
     * @param classificacao the classificacao to set
     */
    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    /**
     * @return the numeroJogadores
     */
    @Documentation("O NUMERO DE JOGADORES PERMITIDOS NO JOGO")
    @Column(name = "NUM_JOGADORES")
    public int getNumeroJogadores() {
        return this.numeroJogadores;
    }

    /**
     * @param numeroJogadores the numeroJogadores to set
     */
    public void setNumeroJogadores(int numeroJogadores) {
        this.numeroJogadores = numeroJogadores;
    }
}