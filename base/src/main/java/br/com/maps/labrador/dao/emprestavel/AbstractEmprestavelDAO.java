package br.com.maps.labrador.dao.emprestavel;

import java.util.List;

import jmine.tec.persist.impl.dao.BaseDAO;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.maps.labrador.domain.emprestavel.enumx.AbstractEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

public class AbstractEmprestavelDAO extends BaseDAO<AbstractEmprestavel> {

    public List<AbstractEmprestavel> findAllByNotMyUser(LabradorUsuario user) {
        Criteria c = this.createCriteria();
        Criteria critUsuario = c.createCriteria("proprietario");
        critUsuario.add(Restrictions.ne("id", user.getId()));
        return this.executeQuery(c);
    }

    public List<AbstractEmprestavel> findAllByMyUser(LabradorUsuario user) {
        Criteria c = this.createCriteria();
        Criteria critUsuario = c.createCriteria("proprietario");
        critUsuario.add(Restrictions.eq("id", user.getId()));
        return this.executeQuery(c);
    }

}
