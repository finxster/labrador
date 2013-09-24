package br.com.maps.labrador.dao;

import java.util.List;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.dao.BaseDAO;
import jmine.tec.persist.impl.hibernate.RestrictionsUtils;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

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
    public List<Mochila> findByNameLocalizacao(String nome, String localizacao) {
        Criteria c = this.createCriteria();
        RestrictionsUtils.addRestrictionILike(c, "nome", nome, MatchMode.ANYWHERE);
        if (localizacao != null) {
            Criteria critLocalizacao = c.createCriteria("localizacao");
            critLocalizacao.add(Restrictions.ilike("nome", localizacao, MatchMode.ANYWHERE));
        }
        return this.executeQuery(c);
    }

    /**
     * Retorna uma lista de {@link Mochila} através de um {@link LabradorUsuario}.
     * 
     * @param mochila {@link Mochila} utilizado na consulta
     * @param labradorUsuario {@link LabradorUsuario} utilizado na consulta
     * @return uma lista de {@link Mochila} através de um {@link LabradorUsuario}.
     * @throws BeanNotFoundException
     */
    public Mochila findByMochilaUsuario(Mochila mochila, LabradorUsuario labradorUsuario) throws BeanNotFoundException {
        Criteria criteria = this.createCriteria();
        RestrictionsUtils.addRestrictionEqId(criteria, "id", mochila);
        Criteria criteriaUsuario = criteria.createCriteria("proprietario");
        RestrictionsUtils.addRestrictionEqId(criteriaUsuario, "id", labradorUsuario);
        return this.executeSingleQuery(criteria);
    }

    /**
     * Retorna uma lista de {@link Mochila} através de uma String @param titulo que representa um atributo de uma {@link Mochila}
     * 
     * @param titulo String utilizada na consulta
     * @return uma {@link Mochila} através de uma String @param titulo que representa um atributo de uma {@link Mochila}
     * @throws BeanNotFoundException
     */
    public List<Mochila> findByNome(String titulo) throws BeanNotFoundException {
        Criteria c = this.createCriteria();
        RestrictionsUtils.addRestrictionILike(c, "nome", titulo, MatchMode.ANYWHERE);
        return this.executeQuery(c);
    }

    /**
     * Retorna uma lista de {@link Mochila} através de uma String @param proprietario que representa um atributo de uma {@link Mochila}
     * 
     * @param proprietario String utilizada na consulta
     * @return uma lista de {@link Mochila} através de uma String @param proprietario que representa um atributo de uma {@link Mochila}
     * @throws BeanNotFoundException
     */
    public List<Mochila> findByProprietario(String proprietario) {
        Criteria c = this.createCriteria();
        if (proprietario != null) {
            Criteria critUsuario = c.createCriteria("proprietario");
            critUsuario.add(Restrictions.ilike("nome", proprietario, MatchMode.ANYWHERE));
        }
        return this.executeQuery(c);
    }

    /**
     * Retorna uma lista de {@link Mochila} através de uma String @param localizacao que representa um atributo de uma {@link Mochila}
     * 
     * @param localizacao String utilizada na consulta
     * @return uma lista de {@link Mochila} através de uma String @param localizacao que representa um atributo de uma {@link Mochila}
     * @throws BeanNotFoundException
     */
    public List<Mochila> findByLocalizacao(String localizacao) {
        Criteria c = this.createCriteria();
        if (localizacao != null) {
            Criteria critUsuario = c.createCriteria("localizacao");
            critUsuario.add(Restrictions.ilike("nome", localizacao, MatchMode.ANYWHERE));
        }
        return this.executeQuery(c);
    }

}
