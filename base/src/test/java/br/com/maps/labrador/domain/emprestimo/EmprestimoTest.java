package br.com.maps.labrador.domain.emprestimo;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.utils.date.Timestamp;
import br.com.maps.labrador.BOBaseTestCase;
import br.com.maps.labrador.domain.emprestavel.AbstractEmprestavel;
import br.com.maps.labrador.domain.emprestimo.enumx.StatusEmprestimo;
import br.com.maps.labrador.domain.livro.Livro;
import br.com.maps.labrador.domain.livro.LivroTest;
import br.com.maps.labrador.domain.usuario.LabradorUsuario;
import br.com.maps.labrador.domain.usuario.LabradorUsuarioTest;

/**
 * Teste de domínio de {@link Emprestimo}.
 * 
 * @author finx
 * @created Aug 26, 2013
 */
public class EmprestimoTest extends BOBaseTestCase<Emprestimo> {

    private static AbstractEmprestavel[] EMPRESTAVEL;

    private static Timestamp[] DATA_HORA;

    private static Timestamp[] DATA_DEVOLUCAO;

    private static StatusEmprestimo[] STATUS;

    private static LabradorUsuario[] TOMADOR;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeTestData() throws BeanNotFoundException {
        EMPRESTAVEL = new LivroTest().getSavedTestData().toArray(new Livro[2]);
        DATA_HORA = new Timestamp[]{ new Timestamp(12345), new Timestamp(98765) };
        DATA_DEVOLUCAO = new Timestamp[]{ new Timestamp(54321), new Timestamp(56789) };
        STATUS = new StatusEmprestimo[]{ StatusEmprestimo.EFETUADO, StatusEmprestimo.DEVOLVIDO };
        TOMADOR = new LabradorUsuarioTest().getSavedTestData().toArray(new LabradorUsuario[2]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillData(int idx, Emprestimo bo) {
        bo.setEmprestavel(EMPRESTAVEL[idx]);
        bo.setData(DATA_HORA[idx]);
        bo.setDataDevolucao(DATA_DEVOLUCAO[idx]);
        bo.setStatus(STATUS[idx]);
        bo.setTomador(TOMADOR[idx]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void compareData(int idx, Emprestimo bo) throws BeanNotFoundException {
        assertEquals(EMPRESTAVEL[idx], bo.getEmprestavel());
        assertEquals(DATA_HORA[idx], bo.getData());
        assertEquals(DATA_DEVOLUCAO[idx], bo.getDataDevolucao());
        assertEquals(STATUS[idx], bo.getStatus());
        assertEquals(TOMADOR[idx], bo.getTomador());
    }

}
