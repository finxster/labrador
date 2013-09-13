package br.com.maps.labrador.domain.modem;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import br.com.maps.labrador.BOBaseTestCase;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavel;
import br.com.maps.labrador.domain.emprestavel.LocalizacaoEmprestavelTest;
import br.com.maps.labrador.domain.emprestavel.enumx.StatusEmprestavel;

/**
 * Um teste para o domínio de {@link Modem}.
 * 
 * @author laercio.duarte
 * @created 23/08/2013
 */

public class ModemTest extends BOBaseTestCase<Modem> {

    private static String[] NOME;

    private static StatusEmprestavel[] STATUS;

    private static LocalizacaoEmprestavel[] LOCALIZACAO;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeTestData() throws BeanNotFoundException {
        NOME = new String[]{ "MODEM1", "MODEM2" };
        STATUS = new StatusEmprestavel[]{ StatusEmprestavel.DISPONIVEL, StatusEmprestavel.EMPRESTADO };
        LOCALIZACAO = new LocalizacaoEmprestavelTest().getSavedTestData().toArray(new LocalizacaoEmprestavel[2]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillData(int idx, Modem bo) {
        bo.setNome(NOME[idx]);
        bo.setStatus(STATUS[idx]);
        bo.setLocalizacao(LOCALIZACAO[idx]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void compareData(int idx, Modem bo) throws BeanNotFoundException {
        assertEquals(NOME[idx], bo.getNome());
        assertEquals(STATUS[idx], bo.getStatus());
        assertEquals(LOCALIZACAO[idx], bo.getLocalizacao());
    }
}
