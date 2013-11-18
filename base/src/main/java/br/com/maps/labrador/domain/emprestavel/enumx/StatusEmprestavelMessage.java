package br.com.maps.labrador.domain.emprestavel.enumx;

import jmine.tec.component.exception.LocalizedMessageHolder;
import jmine.tec.component.exception.MessageCreator;
import jmine.tec.component.exception.MessageCreatorHelper;

/**
 * {@link MessageCreator} para Status de um emprestável.
 * 
 * @author diego.ferreira
 * @created Nov 18, 2013
 */
public enum StatusEmprestavelMessage implements MessageCreator {

    STATUS_DISPONIVEL(0, "emprestavel.messages.disponivel"),

    STATUS_EMPRESTADO(0, "emprestavel.messages.emprestado");

    private final MessageCreator creator;

    public static final String DEFAULT_BUNDLE = "base-messages";

    /**
     * Construtor
     * 
     * @param nargs nargs
     * @param key key
     */
    private StatusEmprestavelMessage(int nargs, String key) {
        this.creator = MessageCreatorHelper.creator(DEFAULT_BUNDLE, key, nargs);
    }

    /**
     * Cria um message holder de acordo com os argumentos
     * 
     * @param arguments arguments
     * @return message holder
     */
    public LocalizedMessageHolder create(Object... arguments) {
        return this.creator.create(arguments);
    }

    /**
     * {@inheritDoc}
     */
    public int getExpectedArguments() {
        return this.creator.getExpectedArguments();
    }
}