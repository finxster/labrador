package br.com.maps.labrador.mail.actor;

import jmine.tec.services.impl.testcase.DefaultRefDBTestCase;

public abstract class EmailActorTest extends DefaultRefDBTestCase {

    public void testParametrosMailSender() {

    }

    @Override
    protected String getSpringMainXMLFilename() {
        return "mail-beans.xml";
    }

    @Override
    protected String[] getResourcesBaseReferencia() {
        return new String[]{ "mail-db.xml" };
    }

    @Override
    protected String getControllerSpringId() {
        return "labradorMailController";
    }
}
