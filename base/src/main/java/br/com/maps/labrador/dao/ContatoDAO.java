package br.com.maps.labrador.dao;

import java.util.List;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.dao.BaseDAO;
import jmine.tec.persist.impl.hibernate.RestrictionsUtils;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;

import br.com.maps.labrador.domain.contato.Contato;
import br.com.maps.labrador.domain.mochila.Mochila;

/**
 * DAO para {@link Contato}.
 * 
 * @author laercio.duarte
 * @created Nov 10, 2013
 */

public class ContatoDAO extends BaseDAO<Contato> {

    /**
     * Retorna uma lista de {@link Contato} através de uma String @param titulo que representa um atributo de uma {@link Mochila}
     * 
     * @param nome String utilizada na consulta
     * @return um {@link Contato} através de uma String @param nome que representa um atributo de um {@link Contato}
     * @throws BeanNotFoundException
     */
    public List<Contato> findByNome(String nome) throws BeanNotFoundException {
        Criteria c = this.createCriteria();
        RestrictionsUtils.addRestrictionILike(c, "nome", nome, MatchMode.ANYWHERE);
        return this.executeQuery(c);
    }
}
