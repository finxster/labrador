package br.com.maps.labrador.dao;

import java.util.List;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.dao.BaseDAO;
import jmine.tec.persist.impl.hibernate.RestrictionsUtils;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestimo.Emprestimo;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * DAO para {@link Emprestimo}.
 * 
 * @author finx
 * @created Aug 27, 2013
 */
public class EmprestimoDAO extends BaseDAO<Emprestimo> {

    /**
     * Retorna uma lista de {@link Emprestimo} através de um {@link Livro}.
     * 
     * @param livro o {@link Livro} utilizado na consulta.
     * @return uma lista de {@link Emprestimo} através de um {@link Livro}.
     */
    public List<Emprestimo> findByLivro(Livro livro) {
        Criteria criteria = this.createCriteria();
        RestrictionsUtils.addRestrictionEqId(criteria, livro);
        return this.executeQuery(criteria);
    }

    /**
     * Retorna um {@link Emprestimo} através de um tomador/{@link LabradorUsuario} e a coisa/objeto que foi tomado/emprestado
     * 
     * @param tomador
     * @param emprestavel
     * @return um {@link Emprestimo} através de um tomador/{@link LabradorUsuario} e a coisa/objeto que foi tomado/emprestado
     * @throws BeanNotFoundException
     */
    public Emprestimo findByTomadorEmprestavel(LabradorUsuario tomador, AbstractEmprestavel emprestavel) throws BeanNotFoundException {
        Criteria criteria = this.createCriteria();
        criteria.createCriteria("tomador").add(Restrictions.eq("id", tomador.getId()));
        criteria.createCriteria("emprestavel").add(Restrictions.eq("id", emprestavel.getId()));
        return this.executeSingleQuery(criteria);
    }

}
