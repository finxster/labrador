package br.com.maps.labrador.domain.projetor;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import br.com.maps.labrador.BOBaseTestCase;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavelTest;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;
import br.com.maps.labrador.domain.usuario.LabradorUsuarioTest;

/**
 * Um teste para o domínio de {@link Projetor}.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */

public class ProjetorTest extends BOBaseTestCase<Projetor> {

    private static String[] NOME;

    private static StatusEmprestavel[] STATUS;

    private static LabradorUsuario[] LABRADOR_USUARIO;

    private static LocalizacaoEmprestavel[] LOCALIZACAO;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeTestData() throws BeanNotFoundException {
        NOME = new String[]{ "PROJETOR1", "PROJETOR2" };
        STATUS = new StatusEmprestavel[]{ StatusEmprestavel.DISPONIVEL, StatusEmprestavel.EMPRESTADO };
        LABRADOR_USUARIO = new LabradorUsuarioTest().getSavedTestData().toArray(new LabradorUsuario[2]);
        LOCALIZACAO = new LocalizacaoEmprestavelTest().getSavedTestData().toArray(new LocalizacaoEmprestavel[2]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillData(int idx, Projetor bo) {
        bo.setNome(NOME[idx]);
        bo.setStatus(STATUS[idx]);
        bo.setProprietario(LABRADOR_USUARIO[idx]);
        bo.setLocalizacao(LOCALIZACAO[idx]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void compareData(int idx, Projetor bo) throws BeanNotFoundException {
        assertEquals(NOME[idx], bo.getNome());
        assertEquals(STATUS[idx], bo.getStatus());
        assertEquals(LABRADOR_USUARIO[idx], bo.getProprietario());
        assertEquals(LOCALIZACAO[idx], bo.getLocalizacao());
    }
}
