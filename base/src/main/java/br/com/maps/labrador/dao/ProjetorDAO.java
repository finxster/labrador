package br.com.maps.labrador.dao;

import java.util.List;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.dao.BaseDAO;
import jmine.tec.persist.impl.hibernate.RestrictionsUtils;

import org.hibernate.Criteria;

import br.com.maps.labrador.domain.projetor.Projetor;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * DAO para {@link Projetor}.
 * 
 * @author laercio.duarte
 * @created Sep 14, 2013
 */
public class ProjetorDAO extends BaseDAO<Projetor> {

    /**
     * Retorna uma lista de {@link Projetor} através de Strings que refletem os atributos de um {@link Projetor}.
     * 
     * @param nome String utilizada na consulta
     * @return uma lista de {@link Projetor} através de uma String que reflete um atributo de um {@link Projetor}.
     */
    public List<Projetor> findByName(String nome) {
        Criteria c = this.createCriteria();
        RestrictionsUtils.addRestrictionILike(c, "nome", nome);

        return this.executeQuery(c);
    }

    /**
     * Retorna uma lista de {@link Projetor} através de um {@link LabradorUsuario}.
     * 
     * @param projetor {@link Projetor} utilizado na consulta
     * @param labradorUsuario {@link LabradorUsuario} utilizado na consulta
     * @return uma lista de {@link Projetor} através de um {@link LabradorUsuario}.
     * @throws BeanNotFoundException
     */
    public Projetor findByProjetorUsuario(Projetor projetor, LabradorUsuario labradorUsuario) throws BeanNotFoundException {
        Criteria criteria = this.createCriteria();
        RestrictionsUtils.addRestrictionEqId(criteria, "id", projetor);
        Criteria criteriaUsuario = criteria.createCriteria("usuario");
        RestrictionsUtils.addRestrictionEqId(criteriaUsuario, "id", labradorUsuario);
        return this.executeSingleQuery(criteria);
    }
}
