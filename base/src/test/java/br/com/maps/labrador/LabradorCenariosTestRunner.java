package br.com.maps.labrador;

import jmine.tec.test.runner.TestRunnerStarter;

/**
 * @author renan.oliveira
 */
public class LabradorCenariosTestRunner {

    /**
     * Starts the test runner
     * 
     * @param args args
     */
    public static void main(String[] args) {
        TestRunnerStarter starter = new TestRunnerStarter("core-test-beans.xml", "core-db.xml");
        starter.setRuntimeXMLs(new String[]{ "core-tests-runner.xml" });
        starter.start();
    }
}