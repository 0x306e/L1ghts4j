package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private final static String L1GHTS4J_PROPS = "l1ghts4j.properties";
    private Properties properties;
    
    public PropertyReader() {
    	properties = new Properties();
    }
    
    public void load() throws FileNotFoundException, IOException {
    	properties.load(new FileInputStream(L1GHTS4J_PROPS));
    }

    public String getValue(String key) {
		return properties.getProperty(key);
    }

}
