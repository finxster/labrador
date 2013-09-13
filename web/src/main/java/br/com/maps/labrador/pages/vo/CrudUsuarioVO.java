package br.com.maps.labrador.pages.vo;

import java.io.Serializable;

import jmine.tec.web.wicket.component.injection.FormInput;
import jmine.tec.web.wicket.component.injection.FormInputProvider;

/**
 * Model para a tela de pesquisa e listagem de usuários.
 *  
 * @author jrenaut
 */
@FormInputProvider
public class CrudUsuarioVO implements Serializable {
	
	private String username;

	/**
	 * @return o nome do usuario
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username o nome do usuario
	 */
    @FormInput
	public void setUsername(String username) {
		this.username = username;
	}
	
}
