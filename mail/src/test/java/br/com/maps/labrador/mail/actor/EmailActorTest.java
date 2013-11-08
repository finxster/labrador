package br.com.maps.labrador.mail.actor;

import jmine.tec.services.impl.testcase.DefaultRefDBTestCase;

import org.junit.Test;

/**
 * Teste do actor {@link EnvioEmailActor}
 * 
 * @author fabio.sakiyama
 * @date Nov 7, 2013
 */
public class EmailActorTest extends DefaultRefDBTestCase {

    /**
     * {@inheritDoc}
     */
    @Test
    public void testParametrosMailSender() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getSpringMainXMLFilename() {
        return "mail-beans.xml";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getResourcesBaseReferencia() {
        return new String[]{ "mail-db.xml" };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getControllerSpringId() {
        return "labradorMailController";
    }
}