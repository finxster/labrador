package br.com.maps.labrador.dao.emprestavel;

import java.util.List;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.dao.BaseDAO;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

public class AbstractEmprestavelDAO extends BaseDAO<AbstractEmprestavel> {

    /**
     * Retorna uma lista de {@link AbstractEmprestavel} através de um {@link LabradorUsuario} que não seja o proprietário.
     * 
     * @param user
     * @return uma lista de {@link AbstractEmprestavel} através de um {@link LabradorUsuario}
     * @throws BeanNotFoundException
     */
    public List<AbstractEmprestavel> findAllByNotMyUser(LabradorUsuario user) {
        Criteria c = this.createCriteria();
        Criteria critUsuario = c.createCriteria("proprietario");
        critUsuario.add(Restrictions.ne("id", user.getId()));
        return this.executeQuery(c);
    }

    /**
     * Retorna uma lista de {@link AbstractEmprestavel} através de um {@link LabradorUsuario}.
     * 
     * @param user
     * @return uma lista de {@link AbstractEmprestavel} através de um {@link LabradorUsuario}
     * @throws BeanNotFoundException
     */
    public List<AbstractEmprestavel> findAllByMyUser(LabradorUsuario user) {
        Criteria c = this.createCriteria();
        Criteria critUsuario = c.createCriteria("proprietario");
        critUsuario.add(Restrictions.eq("id", user.getId()));
        return this.executeQuery(c);
    }
}
