package br.com.maps.labrador;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmine.tec.utils.Tuple;
import jmine.tec.web.wicket.security.WicketSecurityServletFilter;

/**
 * Simple security filter
 * 
 * @author takeshi
 */
public class ApplicationSecurityFilter extends WicketSecurityServletFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void forwardOrRedirect(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.sendRedirect(request.getContextPath() + url);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<Tuple<String, String>> getParameterMapping(String entityName) {
        return Collections.emptyList();
    }

}
