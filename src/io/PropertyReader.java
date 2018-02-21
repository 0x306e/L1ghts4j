package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader implements FileReader {

	protected String path;
    private Properties properties;
    
    public PropertyReader() {
    	properties = new Properties();
    	this.path = "l1ghts4j.properties";
    }
    
    @Override
    public void load() {
    	try {
			properties.load(new FileInputStream(this.path));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
