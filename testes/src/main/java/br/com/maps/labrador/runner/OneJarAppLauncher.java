package br.com.maps.labrador.runner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Class utilitaria utilizada para adicionar programaticamente no class-loader as dependencias do aplicativo que foram inseridas dentro do
 * jar aonde o aplicativo é distribuido.
 * 
 * @author Rafael Volpato
 * @date Nov 23, 2009
 */
public final class OneJarAppLauncher {

    private static final int BUFFER_SIZE = 1024;

    private static final String BUNDLE_MAIN_CLASS = "Bundle-MainClass";

    private static final String PARAM_MAIN_CLASS = "MainClass";

    /**
     * Copy the content from the InputStream into a temporary file that will be deleted when this JVM exits normally
     * 
     * @param pref the preffix
     * @param suffix the suffix
     * @param source the inputStream
     * @return File
     * @throws IOException if an iooexception occurs
     */
    public static File copyToTempFile(final String pref, final String suffix, final InputStream source) throws IOException {
        File file = File.createTempFile(pref, suffix);
        FileOutputStream output = new FileOutputStream(file);
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            int read = -1;
            while ((read = source.read(buffer)) >= 0) {
                output.write(buffer, 0, read);
            }
        } finally {
            try {
                source.close();
            } finally {
                output.close();
            }
        }
        file.deleteOnExit();
        return file;

    }

    /**
     * Executa a chamada do metodo MAIN do aplicativo target
     * 
     * @param name nome
     * @param args argumentos
     * @throws Throwable se ocorrer algum erro
     */
    private static void invokeClass(String name, String[] args) throws Throwable {
        final String mainMethodName = "main";

        Class clazz = ClassLoader.getSystemClassLoader().loadClass(name);
        Method method = clazz.getMethod(mainMethodName, new Class[]{ args.getClass() });
        method.setAccessible(true);
        int mods = method.getModifiers();
        if (method.getReturnType() != void.class || !Modifier.isStatic(mods) || !Modifier.isPublic(mods)) {
            throw new NoSuchMethodException(mainMethodName);
        }

        method.invoke(null, new Object[]{ args });
    }

    /**
     * Verifica se o arquivo é um jar.
     * 
     * @param fileName nome do arquivo
     * @return <code>true</code> se o arquivo for um jar, <code>false</code> caso contrario.
     */
    private static boolean isJar(String fileName) {
        return fileName != null && fileName.toLowerCase().endsWith(".jar");
    }

    /**
     * Main.
     * 
     * @param args args
     * @throws Throwable se ocorrer algum erro
     */
    public static void main(String[] args) throws Throwable {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        if (!(classLoader instanceof URLClassLoader)) {
            throw new IllegalStateException("Classloader type (" + classLoader.getClass().getCanonicalName() + ") not supported!");
        }

        prepareClassloader((URLClassLoader) classLoader);

        String paramMainClass = System.getProperty(PARAM_MAIN_CLASS);
        if (paramMainClass != null) {
            invokeClass(paramMainClass, args);
        } else {
            invokeClass(readManifestAttribute(BUNDLE_MAIN_CLASS), args);
        }
    }

    /**
     * Adiciona no classloader as dependencias do aplicativo incluidas no jar.
     * 
     * @param classLoader classloader
     * @throws Throwable se ocorrer algum erro
     */
    private static void prepareClassloader(URLClassLoader classLoader) throws Throwable {
        Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        addURL.setAccessible(true);

        ProtectionDomain protectionDomain = OneJarAppLauncher.class.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        JarFile jarFile = new JarFile(URLDecoder.decode(codeSource.getLocation().getFile(), "UTF-8"));

        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            if (!jarEntry.isDirectory() && isJar(jarEntry.getName())) {
                File fileElement = copyToTempFile(jarEntry.getName(), ".jar", jarFile.getInputStream(jarEntry));
                addURL.invoke(classLoader, fileElement.toURI().toURL());
            }
        }
    }

    /**
     * Le um atributo do manifesto do jar.
     * 
     * @param attributeName nome do atributo
     * @return o valor do atributo
     * @throws IOException se ocorrer algum erro
     */
    private static String readManifestAttribute(String attributeName) throws IOException {
        ProtectionDomain protectionDomain = OneJarAppLauncher.class.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        URL rootUrl = codeSource.getLocation();
        if (isJar(rootUrl.getFile())) {
            URL rootJarUrl = new URL("jar", "", rootUrl + "!/");
            JarURLConnection uc = (JarURLConnection) rootJarUrl.openConnection();
            Attributes attr = uc.getMainAttributes();
            return attr != null ? attr.getValue(attributeName) : null;
        }
        throw new IllegalStateException("JarRunner is not inside a jar file");
    }

    /**
     * Construtor
     */
    private OneJarAppLauncher() {
        super();
    }
}