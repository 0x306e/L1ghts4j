package account;

import java.io.IOException;

import util.FileReader;

public class SettingReader extends FileReader {

    protected final static String path = "twitter4j.properties";
    private String ID;
    
    @SuppressWarnings("unused")
	private SettingReader() {}
    
    public SettingReader(String id) {
    	super(path);
    	ID = id;
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
