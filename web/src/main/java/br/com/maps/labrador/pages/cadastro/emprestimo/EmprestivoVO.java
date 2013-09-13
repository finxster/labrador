package br.com.maps.labrador.pages.cadastro.emprestimo;

import java.io.Serializable;

import jmine.tec.utils.date.Date;

/**
 * Value Object para a tela de empréstimos.
 * 
 * @author finx
 * @created Sep 6, 2013
 */
public class EmprestivoVO implements Serializable {

    private String livro;

    private Date dataDevolucao;

    /**
     * @return the livro
     */
    public String getLivro() {
        return livro;
    }

    /**
     * @param livro the livro to set
     */
    public void setLivro(String livro) {
        this.livro = livro;
    }

    /**
     * @return the dataDevolucao
     */
    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    /**
     * @param dataDevolucao the dataDevolucao to set
     */
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

}
