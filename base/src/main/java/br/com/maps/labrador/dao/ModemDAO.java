package br.com.maps.labrador.dao;

import java.util.List;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.dao.BaseDAO;
import jmine.tec.persist.impl.hibernate.RestrictionsUtils;

import org.hibernate.Criteria;

import br.com.maps.labrador.domain.modem.Modem;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * DAO para {@link Modem}.
 * 
 * @author laercio.duarte
 * @created Sep 14, 2013
 */

public class ModemDAO extends BaseDAO<Modem> {

    /**
     * Retorna uma lista de {@link Modem} através de Strings que refletem os atributos de um {@link Modem}.
     * 
     * @param nome String utilizada na consulta
     * @return uma lista de {@link Modem} através de uma String que reflete um atributo de um {@link Modem}.
     */
    public List<Modem> findByName(String nome) {
        Criteria c = this.createCriteria();
        RestrictionsUtils.addRestrictionILike(c, "nome", nome);

        return this.executeQuery(c);
    }

    /**
     * Retorna uma lista de {@link Modem} através de um {@link LabradorUsuario}.
     * 
     * @param modem {@link Modem} utilizado na consulta
     * @param labradorUsuario {@link LabradorUsuario} utilizado na consulta
     * @return uma lista de {@link Modem} através de um {@link LabradorUsuario}.
     * @throws BeanNotFoundException
     */
    public Modem findByModemUsuario(Modem modem, LabradorUsuario labradorUsuario) throws BeanNotFoundException {
        Criteria criteria = this.createCriteria();
        RestrictionsUtils.addRestrictionEqId(criteria, "id", modem);
        Criteria criteriaUsuario = criteria.createCriteria("usuario");
        RestrictionsUtils.addRestrictionEqId(criteriaUsuario, "id", labradorUsuario);
        return this.executeSingleQuery(criteria);
    }
}
