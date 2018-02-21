package io;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SettingReader extends PropertyReader {

    protected final static String path = "twitter4j.properties";
    private String ID;
    private Properties properties;
    
	public SettingReader() {
		ID = "default";
		this.load();
	}
    
    public SettingReader(String id) {
    	super();
    	ID = id;
    	this.load();
    }
    
    @Override
    public void load()  {
    	try {
			properties.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public boolean getDebugValue() throws IOException {
    	return Boolean.getBoolean(super.getValue(ID + ".debug"));
    }

    public String getAccessToken() throws IOException {
    	return super.getValue(ID + ".accessToken");
    }

    public String getAccessTokenSecret() throws IOException {
    	return super.getValue(ID + ".accessTokenSecret");
    }

    public String getConsumerKey() throws IOException {
    	return super.getValue(ID + ".consumerKey");
    }

    public String getConsumerSecret() throws IOException {
    	return super.getValue(ID + ".consumerSecret");
    }

}
