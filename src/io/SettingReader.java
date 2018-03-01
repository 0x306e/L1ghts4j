package io;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingReader extends PropertyReader {

    protected final static String path = "twitter4j.properties";
    private String ID;
    
	public SettingReader() {
		ID = "default";
	}
    
    public SettingReader(String id) {
    	super();
    	ID = id;
    }
    
    public void load() throws FileNotFoundException, IOException {
    	super.load();
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
