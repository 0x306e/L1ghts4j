package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public static final String default_path = "l1ghts4j.properties";
    private String path;
    private Properties properties;

    public PropertyReader() {
        this(default_path);
    }

    public PropertyReader(String path) {
        properties = new Properties();
        this.path = path;
    }

    public void load() throws FileNotFoundException, IOException {
        properties.load(new FileInputStream(this.path));
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

    public void setPath(String filePath) {
        try {
            throw new Exception("this method never used.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
