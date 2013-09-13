package br.com.maps.labrador.dao;

import java.util.List;

import jmine.tec.persist.impl.dao.BaseDAO;
import jmine.tec.persist.impl.hibernate.RestrictionsUtils;

import org.hibernate.Criteria;

import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.livro.Livro;

/**
 * DAO para {@link Emprestimo}.
 * 
 * @author finx
 * @created Aug 27, 2013
 */
public class EmprestimoDAO extends BaseDAO<Emprestimo> {

    /**
     * Retorna uma lista de {@link Emprestimo} através de um {@link Livro}.
     * 
     * @param livro o {@link Livro} utilizado na consulta.
     * @return uma lista de {@link Emprestimo} através de um {@link Livro}.
     */
    public List<Emprestimo> findByLivro(Livro livro) {
        Criteria criteria = this.createCriteria();
        RestrictionsUtils.addRestrictionEqId(criteria, livro);
        return executeQuery(criteria);
    }

}
