package br.com.maps.labrador;

import jmine.tec.persist.impl.bussobj.PersistableBusinessObject;

public abstract class BOBaseTestCase<T extends PersistableBusinessObject> extends jmine.tec.persist.test.testcase.BOBaseTestCase<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getTestDataSize() {
        return 2;
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    protected String getSpringMainXMLFilename() {
        return "core-test-beans.xml";
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    protected String[] getResourcesBaseReferencia() {
        return new String[]{ "core-db.xml" };
    }

}
