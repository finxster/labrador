package br.com.maps.labrador.domain.livro;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import br.com.maps.labrador.BOBaseTestCase;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavelTest;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;
import br.com.maps.labrador.domain.usuario.LabradorUsuarioTest;

/**
 * Um teste para o domínio de {@link Livro}.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */
public class LivroTest extends BOBaseTestCase<Livro> {

    private static String[] ISBN10;

    private static String[] ISBN13;

    private static String[] TITULO;

    private static String[] AUTOR;

    private static String[] EDITORA;

    private static LabradorUsuario[] LABRADOR_USUARIO;

    private static StatusEmprestavel[] STATUS;

    private static LocalizacaoEmprestavel[] LOCALIZACAO;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeTestData() throws BeanNotFoundException {
        ISBN10 = new String[]{ "ISBN_1", "ISBN_2" };
        ISBN13 = new String[]{ "ISBN_3", "ISBN_4" };
        TITULO = new String[]{ "Apocalipse", "Bela e Fera" };
        AUTOR = new String[]{ "Autor_1", "Autor_1" };
        EDITORA = new String[]{ "Editora_1", "Editora_2" };
        LABRADOR_USUARIO = new LabradorUsuarioTest().getSavedTestData().toArray(new LabradorUsuario[2]);
        STATUS = new StatusEmprestavel[]{ StatusEmprestavel.DISPONIVEL, StatusEmprestavel.EMPRESTADO };
        LOCALIZACAO = new LocalizacaoEmprestavelTest().getSavedTestData().toArray(new LocalizacaoEmprestavel[2]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillData(int idx, Livro bo) {
        bo.setIsbn10(ISBN10[idx]);
        bo.setIsbn13(ISBN13[idx]);
        bo.setNome(TITULO[idx]);
        bo.setAutor(AUTOR[idx]);
        bo.setEditora(EDITORA[idx]);
        bo.setProprietario(LABRADOR_USUARIO[idx]);
        bo.setStatus(STATUS[idx]);
        bo.setLocalizacao(LOCALIZACAO[idx]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void compareData(int idx, Livro bo) throws BeanNotFoundException {
        assertEquals(ISBN10[idx], bo.getIsbn10());
        assertEquals(ISBN13[idx], bo.getIsbn13());
        assertEquals(TITULO[idx], bo.getNome());
        assertEquals(AUTOR[idx], bo.getAutor());
        assertEquals(EDITORA[idx], bo.getEditora());
        assertEquals(LABRADOR_USUARIO[idx], bo.getProprietario());
        assertEquals(STATUS[idx], bo.getStatus());
        assertEquals(LOCALIZACAO[idx], bo.getLocalizacao());
    }
}
