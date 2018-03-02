package io;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingReader extends PropertyReader {

    protected final static String default_path = "twitter4j.properties";
    private final static String default_key = "default.";
    private String ID;

    public SettingReader() {
        this(default_path, default_key);
    }

    public SettingReader(String path, String id) {
        super(path);
        ID = id;
    }

    public void load() throws FileNotFoundException, IOException {
        super.load();
    }

    public boolean getDebugValue() throws IOException {
        return Boolean.getBoolean(super.getValue(ID + "debug"));
    }

    public String getAccessToken() throws IOException {
        return super.getValue(ID + "accessToken");
    }

    public String getAccessTokenSecret() throws IOException {
        return super.getValue(ID + "accessTokenSecret");
    }

    public String getConsumerKey() throws IOException {
        return super.getValue(ID + "consumerKey");
    }

    public String getConsumerSecret() throws IOException {
        return super.getValue(ID + "consumerSecret");
    }

}
