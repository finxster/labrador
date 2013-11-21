package br.com.maps.labrador;

import jmine.tec.persist.test.testcase.AbstractBOStaticAnalysisTest;

/**
 * Static Analisys Test
 */
public class StaticAnalisysTest extends AbstractBOStaticAnalysisTest {

    /**
     * @return boolean
     */
    @Override
    protected boolean failOnSchemaWarnings() {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     */
    @Override
    protected String getControllerSpringId() {
        return "labradorBaseController";
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     */
    @Override
    protected String[] getResourcesBaseReferencia() {
        return new String[]{ "labrador-base-db.xml" };
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     */
    @Override
    protected String getSpringMainXMLFilename() {
        return "labrador-base-test-beans.xml";
    }

}
