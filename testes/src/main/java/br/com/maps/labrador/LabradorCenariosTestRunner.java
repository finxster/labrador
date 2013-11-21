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
        TestRunnerStarter starter = new TestRunnerStarter("labrador-teste-beans.xml", "labrador-teste-db.xml");
        starter.setRuntimeXMLs(new String[]{ "labrador-teste-test-runner-runtime.xml" });
        starter.start();
    }
}
