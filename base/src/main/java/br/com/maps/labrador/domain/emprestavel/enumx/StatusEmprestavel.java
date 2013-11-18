package br.com.maps.labrador.domain.emprestavel.enumx;

import static br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavelMessage.STATUS_DISPONIVEL;
import static br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavelMessage.STATUS_EMPRESTADO;

import java.util.Locale;

import jmine.tec.component.exception.LocalizedMessageHolder;
import jmine.tec.component.exception.MessageCreator;

/**
 * Status dos empréstimos dos livros.
 * 
 * @author finx
 * @created Sep 6, 2013
 */
public enum StatusEmprestavel implements LocalizedMessageHolder {

    DISPONIVEL(STATUS_DISPONIVEL), EMPRESTADO(STATUS_EMPRESTADO);

    private final LocalizedMessageHolder message;

    /**
     * @return <code>true</code> caso o status seja {@value #DISPONIVEL}, <code>false</code> caso seja outro status
     */
    public boolean isDisponivel() {
        return this.equals(DISPONIVEL);
    }

    /**
     * @return <code>true</code> caso o status seja {@value #EMPRESTADO}, <code>false</code> caso seja outro status
     */
    public boolean isEmprestado() {
        return this.equals(EMPRESTADO);
    }

    /**
     * Construtor
     * 
     * @param creator {@link MessageCreator}.
     */
    private StatusEmprestavel(MessageCreator creator) {
        this.message = creator.create();
    }

    /**
     * {@inheritDoc}
     */
    public String getMessage() {
        return this.message.getMessage();
    }

    /**
     * {@inheritDoc}
     */
    public String getMessage(Locale locale) {
        return this.message.getMessage(locale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
