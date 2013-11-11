package br.com.maps.labrador.pages.messages;

import jmine.tec.component.exception.LocalizedMessageHolder;
import jmine.tec.component.exception.MessageCreator;
import jmine.tec.component.exception.MessageCreatorHelper;

public enum ErrorMessages  implements MessageCreator {
    SENHA_ANTERIOR_DIFERENTE(0, "infra.alterar_senha.senha_anterior_diferente"),
    SENHAS_DIFERENTES(0, "infra.alterar_senha.senhas_diferentes");

    private final MessageCreator delegate;

    /**
     * Construtor
     * 
     * @param nargs nargs
     * @param key key
     */
    private ErrorMessages(int nargs, String key) {
        this.delegate = MessageCreatorHelper.creator("page-error-messages", key, nargs);
    }

    /**
     * {@inheritDoc}
     */
    public LocalizedMessageHolder create(Object... arguments) {
        return this.delegate.create(arguments);
    }

    /**
     * {@inheritDoc}
     */
    public int getExpectedArguments() {
        return this.delegate.getExpectedArguments();
    }

}
