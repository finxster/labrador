package br.com.maps.labrador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import jmine.tec.persist.environment.db.DBEnvironment;
import jmine.tec.persist.environment.db.DBEnvironmentHolder;
import jmine.tec.services.impl.environment.RefDBEnvironment;
import jmine.tec.utils.db.descriptor.FlatReferenceDatabaseBuilder.ReferenceDatabaseDescriptionType;
import jmine.tec.utils.loader.ResourceLoader;
import jmine.tec.utils.log.LogManager;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * Classe principal para iniciar o aplicativo para testes
 */
public final class StartLabradorApplicationH2 {

    private static final int START_WAIT_MILLIS = 5000;

    private static final int CONNECTOR_PORT = 8888;

    private static final int MAX_IDLE_TIME = 1000 * 60 * 60;

    private static final String CONTEXT_PATH = "/labrador";

    private static Server server;

    /**
     * Construtor privado
     */
    private StartLabradorApplicationH2() {
    }

    /**
     * Entry point
     * 
     * @param args os argumentos
     * @throws Exception e
     */
    public static void main(String[] args) throws Exception {
        prepareEnvironment("core-test-beans.xml", "core-db.xml", ReferenceDatabaseDescriptionType.REFERENCE);

        stopPreviousServer();
        server = new Server();
        SocketConnector connector = new SocketConnector();
        connector.setMaxIdleTime(MAX_IDLE_TIME);
        connector.setSoLingerTime(-1);
        connector.setPort(CONNECTOR_PORT);
        server.setConnectors(new Connector[]{ connector });

        WebAppContext bb = new WebAppContext();
        bb.setServer(server);
        bb.setContextPath(CONTEXT_PATH);
        bb.setWar("src/main/webapp");

        server.addHandler(bb);

        System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
        try {
            server.start();
            Thread monitor = new MonitorThread();
            monitor.start();
            while (System.in.available() == 0) {
                Thread.sleep(START_WAIT_MILLIS);
            }
            server.stop();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void stopPreviousServer() {
        try {
            Socket s = new Socket("127.0.0.1", 9785);
            OutputStream output = s.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            writer.write("STOP!");
            writer.newLine();
            writer.flush();
            output.close();
            s.close();
            System.out.println("Stopping previous started server...");
        } catch (IOException e) {
            System.out.println("There is no previous started server to be stopped!");
        }
    }

    /**
     * Prepara o ambiente de testes
     * 
     * @param spring spring
     * @param refdb refdb
     * @param refdbType refdbType
     */
    public static void prepareEnvironment(String spring, String refdb, ReferenceDatabaseDescriptionType refdbType) {
        try {
            new LogManager(new ResourceLoader(), "jmine-tec-test-log.properties", true);
        } catch (IOException e) {
            // OK, no log, then
        }
        DBEnvironmentHolder environmentHolder = DBEnvironmentHolder.getInstance();
        DBEnvironment environment = new RefDBEnvironment(environmentHolder.instantiate(spring, refdb));
        environmentHolder.setEnvironment(environment);
        environment.setRefdbType(refdbType);
        environment.restart();
    }

    private static class MonitorThread extends Thread {

        private ServerSocket socket;

        public MonitorThread() {
            this.setDaemon(true);
            this.setName("StopMonitor");
            try {
                this.socket = new ServerSocket(9785);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            System.out.println("*** running jetty 'stop' thread");
            Socket accept;
            try {
                accept = this.socket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                reader.readLine();
                System.out.println("*** stopping jetty embedded server");
                server.stop();
                accept.close();
                this.socket.close();
                System.exit(0);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
