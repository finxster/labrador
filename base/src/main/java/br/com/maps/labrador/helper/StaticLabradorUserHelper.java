package br.com.maps.labrador.helper;

import br.com.maps.labrador.domain.usuario.LabradorUsuario;

/**
 * {@link LabradorUserHelper} para ser usado no contexto de testes. É definido um usuário padrão para que o teste não dependa de uma sessão.
 * 
 * @author finx
 * @created Sep 27, 2013
 */
public class StaticLabradorUserHelper extends LabradorUserHelper {

    private LabradorUsuario labradorUsuario;

    /**
     * {@inheritDoc}
     */
    @Override
    public LabradorUsuario getCurrentUser() {
        if (this.labradorUsuario == null) {
            return super.getCurrentUser();
        }
        return this.labradorUsuario;
    }

    /**
     * @param labradorUsuario the labradorUsuario to set
     */
    public void setLabradorUsuario(LabradorUsuario labradorUsuario) {
        this.labradorUsuario = labradorUsuario;
    }

}
