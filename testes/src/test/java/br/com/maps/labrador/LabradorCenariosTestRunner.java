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
        TestRunnerStarter starter = new TestRunnerStarter("teste-test-beans.xml", "base-db.xml");
        starter.setRuntimeXMLs(new String[]{ "teste-tests-runner.xml" });
        starter.start();
    }
}