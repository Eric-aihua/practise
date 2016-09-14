package com.eric.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 
 * Read properties by different mechanism
 * 
 * @author aihua.sun
 */
public class LoadConfigFile {
    private static String samePackageProperties = "list2.properties";
    private static String differentPackageProperties = "list3.properties";

    public static void main(String args[]) throws IOException {
        loadSamePackageProperties(samePackageProperties);
        loadDifferentPackageProperties(differentPackageProperties);

    }

    private static void loadDifferentPackageProperties(String differentPackagePropertiesName) throws IOException,
            FileNotFoundException {
        loadProperties(loadFileByClass("/" + differentPackagePropertiesName));
        loadProperties(loadFileByClassResouce("/" + differentPackagePropertiesName));
        loadProperties(loadFileByClassLoader(differentPackagePropertiesName));
        loadProperties(loadFileByThreadContent(differentPackagePropertiesName));
        loadProperties(loadFilesByResourceBundle("list3"));
    }

    private static void loadSamePackageProperties(String samePackagePropertiesName) throws IOException,
            FileNotFoundException {
        loadProperties(loadFileByClassLoader("com/eric/io/" + samePackagePropertiesName));
        loadProperties(loadFileByThreadContent("com/eric/io/" + samePackagePropertiesName));
        loadProperties(loadFileByClass(samePackagePropertiesName));
        loadProperties(loadFileByClassResouce(samePackagePropertiesName));
        loadProperties(loadFilesByResourceBundle("com.eric.io.list2"));
    }

    public static void loadProperties(Properties properties) {
        for (Entry entry : properties.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println();
    }

    /**
     * "."-separated names; all names are absolute; .properties suffix is implied
     * 
     * @return
     */
    public static Properties loadFilesByResourceBundle(String propertiesFile) {
        System.out.println("Generate By resouce Bundle");
        ResourceBundle resourceBundle = ResourceBundle.getBundle(propertiesFile);
        Properties result = new Properties();
        for (Enumeration<?> keys = resourceBundle.getKeys(); keys.hasMoreElements();) {
            final String key = (String) keys.nextElement();
            final String value = resourceBundle.getString(key);

            result.put(key, value);
        }
        return result;
    }

    private static Properties loadFileByClassResouce(String propertiesFile) throws FileNotFoundException, IOException {
        System.out.println("Generate By resouce");
        URL url = LoadConfigFile.class.getResource(propertiesFile);
        return generatePropertiesByIS(new FileInputStream(new File(url.getFile())));

    }

    /**
     * In Class.getResourceAsStream(path), the path is interpreted as a path local to the package of the class you are
     * calling it from. For example calling, String.getResourceAsStream("myfile.txt") will look for a file in your
     * classpath at the following location: "java/lang/myfile.txt". If your path starts with a /, then it will be
     * considered an absolute path, and will start searching from the root of the classpath. So calling
     * String.getResourceAsStream("/myfile.txt") will look at the following location in your in your class path
     * ./myfile.txt.
     * 
     * @return
     * @throws IOException
     */
    public static Properties loadFileByClass(String propertiesFile) throws IOException {
        System.out.println("Generate By Class");
        return generatePropertiesByIS(LoadConfigFile.class.getResourceAsStream(propertiesFile));
    }

    /**
     * ClassLoader.getResourceAsStream(path) will consider all paths to be absolute paths. So calling
     * String.getClassLoader().getResourceAsString("myfile.txt") and
     * String.getClassLoader().getResourceAsString("/myfile.txt") will both look for a file in your classpath at the
     * following location: ./myfile.txt.
     * 
     * @return
     * @throws IOException
     */
    public static Properties loadFileByClassLoader(String propertiesFile) throws IOException {
        System.out.println("Generate By ClassLoader");
        return generatePropertiesByIS(LoadConfigFile.class.getClassLoader().getResourceAsStream(propertiesFile));

    }

    /**
     * In your case, you are loading the class from an Application Server, so your should use
     * Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName) instead of
     * this.getClass().getClassLoader().getResourceAsStream(fileName).
     * 
     * @return
     * @throws IOException
     */
    public static Properties loadFileByThreadContent(String propertiesFile) throws IOException {
        System.out.println("Generate By ThreadContent");
        return generatePropertiesByIS(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(propertiesFile));
    }

    /**
     * Generate Properties Object by InputStream Object
     * 
     * @param is
     *            source input stream
     * @return
     * @throws IOException
     */
    private static Properties generatePropertiesByIS(InputStream is) throws IOException {
        Properties result = new Properties();
        result.load(is);
        return result;
    }
}
