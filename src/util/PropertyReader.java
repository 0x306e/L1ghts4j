package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author 0x306e l1ghts4j.propertiesから情報を読み出すメソッドのクラス
 */
public class PropertyReader {

    private final static String L1GHTS4J_PROPS = "l1ghts4j.properties";
    private Properties properties;
    
    public PropertyReader() {
    	properties = new Properties();
    }
    
    public void load() throws IOException {
    	properties.load(new FileInputStream(L1GHTS4J_PROPS));
    }

    public String getValue(String key) {
		return properties.getProperty(key);
    }

}
