package br.com.maps.labrador.dao;

import java.util.List;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.dao.BaseDAO;
import jmine.tec.persist.impl.hibernate.RestrictionsUtils;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.maps.labrador.domain.contato.Contato;

/**
 * DAO para {@link Contato}.
 * 
 * @author laercio.duarte
 * @created Nov 10, 2013
 */

public class ContatoDAO extends BaseDAO<Contato> {

    /**
     * Retorna uma lista de {@link Contato}
     * 
     * @param nome String utilizada na consulta
     * @param email String utilizada na consulta
     * @return uma lista de {@link Contato}
     * @throws BeanNotFoundException
     */
    public List<Contato> findByNomeOrEmail(String nome, String email) {
        Criteria c = this.createCriteria();
        RestrictionsUtils.addRestrictionILike(c, "nome", nome, MatchMode.ANYWHERE);
        if (email != null) {
            Criteria criteria = c.createCriteria("email");
            criteria.add(Restrictions.ilike("nome", email, MatchMode.ANYWHERE));
        }
        return this.executeQuery(c);
    }
}
