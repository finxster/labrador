package br.com.maps.labrador.domain.livro;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavelTest;

/**
 * Um teste para o domínio de {@link Livro}.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */
public class LivroTest extends AbstractEmprestavelTest<Livro> {

    private static String[] ISBN10;

    private static String[] ISBN13;

    private static String[] AUTOR;

    private static String[] EDITORA;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeTestData() throws BeanNotFoundException {
        super.initializeTestData();
        ISBN10 = new String[]{ "ISBN_1", "ISBN_2" };
        ISBN13 = new String[]{ "ISBN_3", "ISBN_4" };
        AUTOR = new String[]{ "Autor_1", "Autor_1" };
        EDITORA = new String[]{ "Editora_1", "Editora_2" };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillData(int idx, Livro bo) {
        super.fillData(idx, bo);
        bo.setIsbn10(ISBN10[idx]);
        bo.setIsbn13(ISBN13[idx]);
        bo.setAutor(AUTOR[idx]);
        bo.setEditora(EDITORA[idx]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void compareData(int idx, Livro bo) throws BeanNotFoundException {
        super.compareData(idx, bo);
        assertEquals(ISBN10[idx], bo.getIsbn10());
        assertEquals(ISBN13[idx], bo.getIsbn13());
        assertEquals(AUTOR[idx], bo.getAutor());
        assertEquals(EDITORA[idx], bo.getEditora());
    }
}
