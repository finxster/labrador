package br.com.maps.labrador;

import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;

public abstract class BOBaseTestCase<T extends PersistableBusinessObject> extends jmine.tec.persist.test.testcase.BOBaseTestCase<T> {

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    protected String[] getResourcesBaseReferencia() {
        return new String[]{ "base-db.xml" };
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    protected String getSpringMainXMLFilename() {
        return "base-test-beans.xml";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getTestDataSize() {
        return 2;
    }

}
