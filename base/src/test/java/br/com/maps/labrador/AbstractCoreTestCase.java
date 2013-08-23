package br.com.maps.labrador;

import jmine.tec.persist.test.junit4.DBEnv;
import jmine.tec.persist.test.junit4.DBTestCase;

/**
 * AbstractCore TestCase
 */
@DBEnv(refdb = "core-db.xml", spring = "core-test-beans.xml")
public abstract class AbstractCoreTestCase extends DBTestCase {

}
