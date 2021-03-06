package br.com.maps.labrador.dao;

import java.util.List;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.dao.BaseDAO;
import jmine.tec.persist.impl.hibernate.RestrictionsUtils;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

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
     * Retorna uma lista de {@link Modem} através das Strings @param nome and @param localizacao que refletem atributos de um {@link Modem}.
     * 
     * @param nome String utilizada na consulta
     * @param localizacao String utilizada na consulta
     * @return uma lista de {@link Modem} através das Strings @param nome and @param localizacao que refletem atributos de um {@link Modem}.
     */
    public List<Modem> findByNameLocalizacao(String nome, String localizacao) {
        Criteria c = this.createCriteria();
        RestrictionsUtils.addRestrictionILike(c, "nome", nome, MatchMode.ANYWHERE);
        if (localizacao != null) {
            Criteria critLocalizacao = c.createCriteria("localizacao");
            critLocalizacao.add(Restrictions.ilike("nome", localizacao, MatchMode.ANYWHERE));
        }
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
        Criteria criteriaUsuario = criteria.createCriteria("proprietario");
        RestrictionsUtils.addRestrictionEqId(criteriaUsuario, "id", labradorUsuario);
        return this.executeSingleQuery(criteria);
    }

    /**
     * Retorna uma lista de {@link Modem} através de uma String @param titulo que representa um atributo de uma {@link Modem}
     * 
     * @param titulo String utilizada na consulta
     * @return uma {@link Modem} através de uma String @param titulo que representa um atributo de uma {@link Modem}
     * @throws BeanNotFoundException
     */
    public List<Modem> findByNome(String titulo) {
        Criteria c = this.createCriteria();
        RestrictionsUtils.addRestrictionILike(c, "nome", titulo, MatchMode.ANYWHERE);
        return this.executeQuery(c);
    }

    /**
     * Retorna uma lista de {@link Modem} através de uma String @param proprietario que representa um atributo de uma {@link Modem}
     * 
     * @param proprietario String utilizada na consulta
     * @return uma lista de {@link Modem} através de uma String @param proprietario que representa um atributo de uma {@link Modem}
     * @throws BeanNotFoundException
     */
    public List<Modem> findByProprietario(String proprietario) {
        Criteria c = this.createCriteria();
        if (proprietario != null) {
            Criteria critUsuario = c.createCriteria("proprietario");
            critUsuario.add(Restrictions.ilike("nome", proprietario, MatchMode.ANYWHERE));
        }
        return this.executeQuery(c);
    }

    /**
     * Retorna uma lista de {@link Modem} através de uma String @param localizacao que representa um atributo de uma {@link Modem}
     * 
     * @param localizacao String utilizada na consulta
     * @return uma lista de {@link Modem} através de uma String @param localizacao que representa um atributo de uma {@link Modem}
     * @throws BeanNotFoundException
     */
    public List<Modem> findByLocalizacao(String localizacao) {
        Criteria c = this.createCriteria();
        if (localizacao != null) {
            Criteria critUsuario = c.createCriteria("localizacao");
            critUsuario.add(Restrictions.ilike("nome", localizacao, MatchMode.ANYWHERE));
        }
        return this.executeQuery(c);
    }
}
