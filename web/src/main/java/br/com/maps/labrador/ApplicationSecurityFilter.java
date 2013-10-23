package br.com.maps.labrador;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmine.tec.utils.Tuple;
import jmine.tec.utils.UnmodifiableTuple;
import jmine.tec.web.wicket.security.WicketSecurityServletFilter;
import br.com.maps.labrador.chinese.EmprestavelChineseWallEntity;
import br.com.maps.labrador.domain.emprestavel.Emprestavel;

/**
 * Simple security filter
 * 
 * @author takeshi
 */
public class ApplicationSecurityFilter extends WicketSecurityServletFilter {

    private final HashMap<String, Collection<Tuple<String, String>>> permissionMapping;

    public ApplicationSecurityFilter() {
        this.permissionMapping = new HashMap<String, Collection<Tuple<String, String>>>();
        this.permissionMapping.put(this.getPermissionName(Emprestavel.class),
                Collections.singleton(this.createTuple(EmprestavelChineseWallEntity.FILTER_NAME, EmprestavelChineseWallEntity.PARAM_NAME)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void forwardOrRedirect(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.sendRedirect(request.getContextPath() + url);
    }

    /**
     * Cria uma tupla imutável
     * 
     * @param head head
     * @param tail tail
     * @return Tuple
     */
    private Tuple<String, String> createTuple(String head, String tail) {
        return new UnmodifiableTuple<String, String>(head, tail);
    }

    /**
     * Retorna o nome da permissão, dada a entidade que contém o filtro.
     * 
     * @param klass klass
     * @return String
     */
    private String getPermissionName(Class klass) {
        return klass.getName().substring(klass.getName().lastIndexOf('.') + 1).toUpperCase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<Tuple<String, String>> getParameterMapping(String entityName) {
        return this.permissionMapping.get(entityName.toUpperCase());
    }
}
