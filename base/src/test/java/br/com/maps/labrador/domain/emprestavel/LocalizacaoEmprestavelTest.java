package br.com.maps.labrador.domain.emprestavel;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import br.com.maps.labrador.BOBaseTestCase;

/**
 * Teste de domínio de {@link LocalizacaoEmprestavel}.
 * 
 * @author finx
 * @created Sep 13, 2013
 */

public class LocalizacaoEmprestavelTest extends BOBaseTestCase<LocalizacaoEmprestavel> {

    private static String[] NOME;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeTestData() throws BeanNotFoundException {
        NOME = new String[]{ "nome1", "nome2" };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillData(int idx, LocalizacaoEmprestavel bo) {
        bo.setNome(NOME[idx]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void compareData(int idx, LocalizacaoEmprestavel bo) throws BeanNotFoundException {
        assertEquals(NOME[idx], bo.getNome());
    }

}
