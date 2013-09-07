package br.com.maps.labrador;

import jmine.tec.component.exception.LocalizedMessageHolder;
import jmine.tec.component.exception.MessageCreator;
import jmine.tec.component.exception.MessageCreatorHelper;

/**
 * Mensagens internacionalizáveis do componente labrador-base.
 * 
 * @author finx
 * @created Sep 6, 2013
 */
public enum LabradorBaseMessages implements MessageCreator {

    NAO_EH_POSSIVEL_EMPRESTAR_UM_LIVRO_PROPRIO(0, "nao.eh.possivel.emprestar.um.livro.proprio"),

    LIVRO_NAO_DISPONIVEL_PARA_EMPRESTIMO(1, "livro.nao.disponivel.para.emprestimo"),

    LIVR_NAO_ENCONTRADO_PARA_USUARIO(2, "livro.nao.encontrado.para.o.usuario.informado");

    private final MessageCreator delegate;

    /**
     * Construtor
     * 
     * @param nargs nargs
     * @param key key
     */
    private LabradorBaseMessages(int nargs, String key) {
        this.delegate = MessageCreatorHelper.creator("core-messages", key, nargs);
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
