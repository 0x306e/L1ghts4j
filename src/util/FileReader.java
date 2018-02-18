package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public abstract class FileReader {

	protected String path;
    protected Properties properties;
    
	protected FileReader() {}
    
    public FileReader(String file) {
    	this.path = file;
    	properties = new Properties();
    }
    
    public void load() throws FileNotFoundException, IOException {
    	properties.load(new FileInputStream(path));
    }

    public String getValue(String key) {
		return properties.getProperty(key);
    }

}
