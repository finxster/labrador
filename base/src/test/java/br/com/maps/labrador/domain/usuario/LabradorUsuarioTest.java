package br.com.maps.labrador.domain.usuario;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.security.impl.domain.User;
import jmine.tec.security.impl.domain.UserTest;
import br.com.maps.labrador.BOBaseTestCase;

public class LabradorUsuarioTest extends BOBaseTestCase<LabradorUsuario> {

    private static User[] USER;

    private static String[] NOME;

    private static String[] EMAIL;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeTestData() throws BeanNotFoundException {
        USER = new UserTest().getSavedTestData().toArray(new User[2]);
        NOME = new String[]{ "nome1", "nome2" };
        EMAIL = new String[]{ "usuario1@maps.com.br", "usuario2@maps.com.br" };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillData(int idx, LabradorUsuario bo) {
        bo.setUser(USER[idx]);
        bo.setNome(NOME[idx]);
        bo.setEmail(EMAIL[idx]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void compareData(int idx, LabradorUsuario labradorUsuario) throws BeanNotFoundException {
        assertEquals(labradorUsuario.getUser(), USER[idx]);
        assertEquals(labradorUsuario.getNome(), NOME[idx]);
        assertEquals(labradorUsuario.getEmail(), EMAIL[idx]);
        assertSame(labradorUsuario.getUserName(), labradorUsuario.getUser().getUsername());
    }
}
