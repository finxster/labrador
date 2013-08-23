package br.com.maps.labrador.domain;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.test.testcase.BOBaseTestCase;

/**
 * Um teste para o domínio de {@link Livro}.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */
public class LivroTest extends BOBaseTestCase<Livro> {

    private static String[] NOME;

    private static String[] DESCRICAO;

    @Override
    protected void initializeTestData() throws BeanNotFoundException {
        NOME = new String[]{ "Laercio", "Finx" };
        DESCRICAO = new String[]{ "Apocalipse", "Bela e Fera" };
    }

    @Override
    protected int getTestDataSize() {
        return 2;
    }

    @Override
    protected void fillData(int idx, Livro bo) {
        bo.setNome(NOME[idx]);
        bo.setDescricao(DESCRICAO[idx]);
    }

    @Override
    protected void compareData(int idx, Livro bo) throws BeanNotFoundException {
        assertEquals(NOME[idx], bo.getNome());
        assertEquals(DESCRICAO[idx], bo.getDescricao());
    }

    @Override
    protected String getSpringMainXMLFilename() {
        return "core-test-beans.xml";
    }

    @Override
    protected String[] getResourcesBaseReferencia() {
        return new String[]{ "core-db.xml" };
    }
}
