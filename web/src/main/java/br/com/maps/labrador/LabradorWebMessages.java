package br.com.maps.labrador;

import jmine.tec.component.exception.LocalizedMessageHolder;
import jmine.tec.component.exception.MessageCreator;
import jmine.tec.component.exception.MessageCreatorHelper;

public enum LabradorWebMessages implements MessageCreator{
	
	FALHA_OBTER_DADOS_ISBN(1,"falha.ao.obter.dados.a.partir.do.isbn"),
	
	FALHA_OBTER_DADOS_ISBN_REDE_INDISPONIVEL(1,"falha.ao.obter.dados.a.partir.do.isbn.rede.indisponivel");
	
	private final MessageCreator delegate;
	
	  /**
     * Construtor
     * 
     * @param nargs nargs
     * @param key key
     */
    private LabradorWebMessages(int nargs, String key) {
        this.delegate = MessageCreatorHelper.creator("webapp-messages", key, nargs);
    }


	public LocalizedMessageHolder create(Object... arguments) {
		return this.delegate.create(arguments);
	}

	public int getExpectedArguments() {
		return this.delegate.getExpectedArguments();
	}

}
