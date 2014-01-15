package br.com.maps.labrador;

import jmine.tec.persist.test.junit4.DBEnv;
import jmine.tec.persist.test.junit4.DBTestCase;

/**
 * AbstractCore TestCase
 */
@DBEnv(refdb = "labrador-base-db.xml", spring = "labrador-base-test.xml")
public abstract class AbstractBaseTestCase extends DBTestCase {

}
