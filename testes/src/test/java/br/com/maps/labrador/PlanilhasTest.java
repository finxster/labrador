package br.com.maps.labrador;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.exception.PersistenceException;
import jmine.tec.services.impl.ServicesIntegrationTestCase;
import jmine.tec.services.impl.junitx.IntegrationTestSuite;
import junit.framework.Test;

/**
 * @author renan.oliveira
 */
public class PlanilhasTest extends ServicesIntegrationTestCase {

    /**
     * Esse metodo é necessário pois alguns TestRunner's talvez não consigam executar essa classe de teste devido ao fato dela nao possuir
     * nenhum metodo iniciado com "test".
     * 
     * @return uma instancia dessa classe de teste
     * @throws PersistenceException
     * @throws BeanNotFoundException
     * @throws IOException
     */
    public static Test suite() throws PersistenceException, BeanNotFoundException, IOException {
        return new IntegrationTestSuite(PlanilhasTest.class);
    }

    public PlanilhasTest() {
        super();
    }

    /**
     * Construtor.
     * 
     * @param name nome do teste.
     */
    public PlanilhasTest(String name) {
        super(name);
    }

    /**
     * Construtor utilizado para obter uma instancia de um teste de integracao que vai executar o arquivo de teste definido.
     * 
     * @param url arquivo de teste que vai ser executado
     */
    public PlanilhasTest(URL url) {
        super(url);
    }

    /**
     * Reinicializo o BD apos cada teste.
     */
    @Override
    protected void afterTest() throws Throwable {
        this.getEnvironment().restart();
    }

    /**
     * {@inheritDoc}
     * 
     * @return curvasBuilderController.
     */
    @Override
    protected final String getControllerSpringId() {
        return "servicesController";
    }

    /**
     * @return lista contendo os diretorios dos testes que devem ser executados.
     */
    @Override
    protected List<String> getInputFiles() {

        List<String> testes = new ArrayList<String>();

        testes.add("cenarios");

        return testes;
    }

    /**
     * Resources para base subir base de referência.
     */
    @Override
    protected String[] getResourcesBaseReferencia() {
        String[] out = { "base-db.xml" };
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final String getSpringMainXMLFilename() {
        return "teste-test-beans.xml";
    }

    @Override
    protected URL getTestInput() {
        URL url = super.getTestInput();
        if (url == null) {
            url = this.getClass().getResource(this.getName());
        }
        if (url == null) {
            url = this.getClass().getClassLoader().getResource(this.getName());
        }
        if (url == null) {
            try {
                // WTF?!
                URL base = this.getClass().getClassLoader().getResource("cenarios");

                url = new URL("file", "", base.getFile() + "/.." + this.getName());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if (url == null) {
            throw new RuntimeException("cannot find resource: " + this.getName());
        }
        return url;
    }

}
