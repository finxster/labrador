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

    private static String[] ISBN;

    private static String[] TITULO;

    private static String[] AUTOR;

    private static String[] EDITORA;

    @Override
    protected void initializeTestData() throws BeanNotFoundException {
        ISBN = new String[]{ "ISBN_1", "ISBN_2" };
        TITULO = new String[]{ "Apocalipse", "Bela e Fera" };
        AUTOR = new String[]{ "Autor_1", "Autor_1" };
        EDITORA = new String[]{ "Editora_1", "Editora_2" };
    }

    @Override
    protected int getTestDataSize() {
        return 2;
    }

    @Override
    protected void fillData(int idx, Livro bo) {
        bo.setIsbn(ISBN[idx]);
        bo.setTitulo(TITULO[idx]);
        bo.setAutor(AUTOR[idx]);
        bo.setEditora(EDITORA[idx]);
    }

    @Override
    protected void compareData(int idx, Livro bo) throws BeanNotFoundException {
        assertEquals(ISBN[idx], bo.getIsbn());
        assertEquals(TITULO[idx], bo.getTitulo());
        assertEquals(AUTOR[idx], bo.getAutor());
        assertEquals(EDITORA[idx], bo.getEditora());
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
