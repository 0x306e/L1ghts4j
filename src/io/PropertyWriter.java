package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyWriter {
    private static String default_path = "l1ghts4j.properties";
    private String path;
    private Properties properties;

    public PropertyWriter() {
        this(default_path);
    }

    public PropertyWriter(String path) {
        properties = new Properties();
        this.path = path;
        try {
            properties.load(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] " + path + " does not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[ERROR] IOException has occurred.");
            e.printStackTrace();
        }
    }

    public void load() throws FileNotFoundException, IOException {
        properties.load(new FileInputStream(path));
    }

    public void setValue(String key, String value) {
        properties.setProperty(key, value);
    }

    public void store() throws IOException {
        properties.store(new FileWriter(path), "");
    }

}
