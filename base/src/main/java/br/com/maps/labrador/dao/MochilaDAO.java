package br.com.maps.labrador.dao;

import java.util.List;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.dao.BaseDAO;
import jmine.tec.persist.impl.hibernate.RestrictionsUtils;

import org.hibernate.Criteria;

import br.com.maps.labrador.domain.mochila.Mochila;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * DAO para {@link Mochila}.
 * 
 * @author laercio.duarte
 * @created Sep 14, 2013
 */

public class MochilaDAO extends BaseDAO<Mochila> {

    /**
     * Retorna uma lista de {@link Mochila} através de Strings que refletem os atributos de um {@link Mochila}.
     * 
     * @param nome String utilizada na consulta
     * @return uma lista de {@link Mochila} através de uma String que reflete um atributo de um {@link Mochila}.
     */
    public List<Mochila> findByName(String nome) {
        Criteria c = this.createCriteria();
        RestrictionsUtils.addRestrictionILike(c, "nome", nome);

        return this.executeQuery(c);
    }

    public Mochila findByMochilaUsuario(Mochila mochila, LabradorUsuario labradorUsuario) throws BeanNotFoundException {
        Criteria criteria = this.createCriteria();
        RestrictionsUtils.addRestrictionEqId(criteria, "id", mochila);
        Criteria criteriaUsuario = criteria.createCriteria("usuario");
        RestrictionsUtils.addRestrictionEqId(criteriaUsuario, "id", labradorUsuario);
        return this.executeSingleQuery(criteria);
    }
}
