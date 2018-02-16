package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyWriter {
    private final static String L1GHTS4J_PROPS = "l1ghts4j.properties";
    private Properties properties;

    public PropertyWriter() {
        properties = new Properties();

        try {
            properties.load(new FileInputStream(L1GHTS4J_PROPS));
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] " + L1GHTS4J_PROPS + " does not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[ERROR] IOException has occurred.");
            e.printStackTrace();
        }
    }
    
    public void load() throws FileNotFoundException, IOException {
    	properties.load(new FileInputStream(L1GHTS4J_PROPS));
    }
    
    public void setValue(String key, String value) {
    	properties.setProperty(key, value);
    }
    
    public void dump() throws IOException {
    	properties.store(new FileWriter(L1GHTS4J_PROPS), "");
    }

}
