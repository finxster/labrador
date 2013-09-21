package br.com.maps.labrador.domain.emprestavel;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import br.com.maps.labrador.BOBaseTestCase;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;
import br.com.maps.labrador.domain.usuario.LabradorUsuarioTest;

/**
 * Teste de domínio para {@link AbstractEmprestavel}
 * 
 * @author finx
 * @created Sep 20, 2013
 */
public abstract class AbstractEmprestavelTest<T extends AbstractEmprestavel> extends BOBaseTestCase<T> {

    private static String[] NOME;

    private static LabradorUsuario[] PROPRIETARIO;

    private static StatusEmprestavel[] STATUS;

    private static LocalizacaoEmprestavel[] LOCALIZACAO;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeTestData() throws BeanNotFoundException {
        NOME = new String[]{ "nome1", "nome2" };
        PROPRIETARIO = new LabradorUsuarioTest().getSavedTestData().toArray(new LabradorUsuario[2]);
        STATUS = new StatusEmprestavel[]{ StatusEmprestavel.DISPONIVEL, StatusEmprestavel.EMPRESTADO };
        LOCALIZACAO = new LocalizacaoEmprestavelTest().getSavedTestData().toArray(new LocalizacaoEmprestavel[2]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillData(int idx, T bo) {
        bo.setNome(NOME[idx]);
        bo.setStatus(STATUS[idx]);
        bo.setProprietario(PROPRIETARIO[idx]);
        bo.setLocalizacao(LOCALIZACAO[idx]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void compareData(int idx, T bo) throws BeanNotFoundException {
        assertEquals(NOME[idx], bo.getNome());
        assertEquals(PROPRIETARIO[idx], bo.getProprietario());
        assertEquals(STATUS[idx], bo.getStatus());
        assertEquals(LOCALIZACAO[idx], bo.getLocalizacao());
    }

}
