package br.com.maps.labrador;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import jmine.tec.persist.api.dao.BeanNotFoundException;
import jmine.tec.persist.impl.exception.PersistenceException;
import jmine.tec.services.impl.ServicesIntegrationTestCase;
import jmine.tec.services.impl.junitx.IntegrationTestSuite;
/**
 * 
 * @author renan.oliveira
 *
 */
public class PlanilhasTest extends ServicesIntegrationTestCase {

    /**
     * Resources para base subir base de referência.
     */
    @Override
    protected String[] getResourcesBaseReferencia() {
        String[] out = { "core-db.xml" };
        return out;
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
     * {@inheritDoc}
     */
    @Override
    protected final String getSpringMainXMLFilename() {
        return "core-test-beans.xml";
    }

    /**
     * Reinicializo o BD apos cada teste.
     */
    @Override
    protected void afterTest() throws Throwable {
        this.getEnvironment().restart();
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

}
