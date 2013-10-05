package br.com.maps.labrador.domain.grupo;

import java.util.HashSet;
import java.util.Set;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.security.impl.domain.ChineseWallCredentialTest;
import jmine.tec.security.impl.domain.Credential;
import jmine.tec.security.impl.domain.LabradorGrupo;
import jmine.tec.security.impl.domain.UrlCredentialTest;
import br.com.maps.labrador.BOBaseTestCase;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;
import br.com.maps.labrador.domain.usuario.LabradorUsuarioTest;

public class LabradorGrupoTest extends BOBaseTestCase<LabradorGrupo> {

    private static String[] names;

    private static Set<Credential>[] credentials;

    private static LabradorUsuario[] PROPRIETARIO;

    private static LabradorUsuario[] ADMINISTRADORES;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeTestData() {
        names = new String[]{ "group1", "group2" };

        Set<Credential> credentials1 = new HashSet(new UrlCredentialTest().getSavedTestData());
        Set<Credential> credentials2 = new HashSet(new ChineseWallCredentialTest().getSavedTestData());

        credentials = new Set[]{ credentials1, credentials2 };

        PROPRIETARIO = new LabradorUsuarioTest().getSavedTestData().toArray(new LabradorUsuario[2]);
        ADMINISTRADORES = new LabradorUsuarioTest().getSavedTestData().toArray(new LabradorUsuario[2]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillData(int idx, LabradorGrupo grupo) {
        grupo.setName(names[idx]);
        grupo.getCredentials().clear();
        grupo.getCredentials().addAll(credentials[idx]);
        grupo.setProprietario(PROPRIETARIO[idx]);
        grupo.getAdministradores().clear();
        grupo.addAdministrador(ADMINISTRADORES[idx]);
    }

    @Override
    protected void compareData(int idx, LabradorGrupo grupo) throws BeanNotFoundException {
        assertEquals(names[idx], grupo.getName());
        assertEquals(credentials[idx].size(), grupo.getCredentials().size());
        assertEquals(PROPRIETARIO[idx], grupo.getProprietario());
        assertEquals(ADMINISTRADORES[idx], grupo.getAdministradores().iterator().next());
    }

}
