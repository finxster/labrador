package br.com.maps.labrador.dao.emprestavel;

import java.util.List;

import jmine.tec.persist.impl.dao.BaseDAO;

import org.hibernate.Criteria;

import br.com.maps.labrador.domain.emprestavel.Emprestavel;

/**
 * DAO de {@link Emprestavel}.
 * 
 * @author finx
 * @created Sep 15, 2013
 */
public class EmprestavelDAO extends BaseDAO<Emprestavel> {

    /**
     * Retorna uma lista de {@link Emprestavel}
     * 
     * @return uma lista de {@link Emprestavel}
     */
    public List<Emprestavel> findAllWithoutPaging() {
        Criteria criteria = this.createCriteria();
        // criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }

}
