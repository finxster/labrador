package br.com.maps.labrador;

import jmine.tec.persist.test.junit4.DBEnv;
import jmine.tec.persist.test.junit4.DBTestCase;

/**
 * AbstractCore TestCase
 */
@DBEnv(refdb = "base-db.xml", spring = "base-test-beans.xml")
public abstract class AbstractBaseTestCase extends DBTestCase {

}
