package br.com.maps.labrador;

import jmine.tec.component.exception.LocalizedMessageHolder;
import jmine.tec.component.exception.MessageCreator;
import jmine.tec.component.exception.MessageCreatorHelper;


/**
 * Mensagens de Help das paginas
 */
public enum HelpMessages implements MessageCreator {
    CONTROLE_ACESSO(0, "infra.acesso.help"),
    ALTERAR_SENHA(0, "infra.alterar_senha.help");

    private final MessageCreator delegate;

    /**
     * Construtor
     * 
     * @param nargs nargs
     * @param key key
     */
    private HelpMessages(int nargs, String key) {
        this.delegate = MessageCreatorHelper.creator("webapp-messages", key, nargs);
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
