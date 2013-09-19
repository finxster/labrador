package br.com.maps.labrador.dao;

import java.util.List;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.dao.BaseDAO;
import jmine.tec.persist.impl.hibernate.RestrictionsUtils;

import org.hibernate.Criteria;

import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * DAO para {@link Livro}.
 * 
 * @author laercio.duarte
 * @created Aug 29, 2013
 */
public class LivroDAO extends BaseDAO<Livro> {

    /**
     * Retorna uma lista de {@link Livro} através de Strings que refletem os atributos de um {@link Livro}.
     * 
     * @param isbn10 String utilizada na consulta
     * @param isbn13 String utilizada na consulta
     * @param titulo String utilizada na consulta
     * @param autor String utilizada na consulta
     * @param editora String utilizada na consulta
     * @return uma lista de {@link Livro} através de Strings que refletem os atributos de um {@link Livro}.
     */
    public List<Livro> findByName(String isbn10, String isbn13, String titulo, String autor, String editora) {
        Criteria c = this.createCriteria();
        RestrictionsUtils.addRestrictionEq(c, "isbn10", isbn10);
        RestrictionsUtils.addRestrictionILike(c, "isbn13", isbn13);
        RestrictionsUtils.addRestrictionILike(c, "titulo", titulo);
        RestrictionsUtils.addRestrictionILike(c, "autor", autor);
        RestrictionsUtils.addRestrictionILike(c, "editora", editora);

        return this.executeQuery(c);
    }

    /**
     * Retorna uma lista de {@link Livro} através de um {@link LabradorUsuario}.
     * 
     * @param livro {@link Livro} utilizado na consulta
     * @param labradorUsuario {@link LabradorUsuario} utilizado na consulta
     * @return uma lista de {@link Livro} através de um {@link LabradorUsuario}.
     * @throws BeanNotFoundException
     */
    public Livro findByLivroUsuario(Livro livro, LabradorUsuario labradorUsuario) throws BeanNotFoundException {
        Criteria criteria = this.createCriteria();
        RestrictionsUtils.addRestrictionEqId(criteria, "id", livro);
        Criteria criteriaUsuario = criteria.createCriteria("usuario");
        RestrictionsUtils.addRestrictionEqId(criteriaUsuario, "id", labradorUsuario);
        return this.executeSingleQuery(criteria);
    }
}
