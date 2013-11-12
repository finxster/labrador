package br.com.maps.labrador.domain.contato;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import br.com.maps.labrador.BOBaseTestCase;

/**
 * Um teste para o domínio de {@link Contato}.
 * 
 * @author laercio.duarte
 * @created Nov 10, 2013
 */
public class ContatoTest extends BOBaseTestCase<Contato> {

    private static String[] NOME;

    private static String[] EMAIL;

    private static String[] ASSUNTO;

    private static String[] MENSAGEM;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeTestData() throws BeanNotFoundException {
        NOME = new String[]{ "Nome 1", "Nome 2" };
        EMAIL = new String[]{ "teste1@teste", "teste2@teste" };
        ASSUNTO = new String[]{ "Interesse em contato 1", "Interesse em contato 2" };
        MENSAGEM = new String[]{ "Olá, feedback para o Labrador 1", "Olá, feedback para o Labrador 2" };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillData(int idx, Contato bo) {
        bo.setNome(NOME[idx]);
        bo.setEmail(EMAIL[idx]);
        bo.setAssunto(ASSUNTO[idx]);
        bo.setMensagem(MENSAGEM[idx]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void compareData(int idx, Contato bo) throws BeanNotFoundException {
        assertEquals(NOME[idx], bo.getNome());
        assertEquals(EMAIL[idx], bo.getEmail());
        assertEquals(ASSUNTO[idx], bo.getAssunto());
        assertEquals(MENSAGEM[idx], bo.getMensagem());
    }

}
