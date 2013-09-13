package br.com.maps.labrador.domain.livro;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import br.com.maps.labrador.BOBaseTestCase;

/**
 * Teste de domínio de {@link LocalizacaoLivro}.
 * 
 * @author finx
 * @created Sep 13, 2013
 */
public class LocalizacaoLivroTest extends BOBaseTestCase<LocalizacaoLivro> {

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
    protected void fillData(int idx, LocalizacaoLivro bo) {
        bo.setNome(NOME[idx]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void compareData(int idx, LocalizacaoLivro bo) throws BeanNotFoundException {
        assertEquals(NOME[idx], bo.getNome());
    }

}
