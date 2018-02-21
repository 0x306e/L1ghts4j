package account;

import java.io.IOException;

import io.SettingReader;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class ConfigurationAdapter {

    public static Configuration getConfig(String id) throws IOException {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        SettingReader sr = new SettingReader(id);

        builder.setDebugEnabled(sr.getDebugValue());
        builder.setOAuthConsumerKey(sr.getConsumerKey());
        builder.setOAuthConsumerSecret(sr.getConsumerSecret());
        builder.setOAuthAccessToken(sr.getAccessToken());
        builder.setOAuthAccessTokenSecret(sr.getAccessTokenSecret());

        Configuration conf = builder.build();
        return conf;
    }
    
    public static Configuration getConsumer() throws IOException {
    	ConfigurationBuilder builder = new ConfigurationBuilder();
    	SettingReader sr = new SettingReader();
    	builder.setOAuthConsumerKey(sr.getConsumerKey());
    	builder.setOAuthConsumerSecret(sr.getConsumerSecret());
    	
    	return builder.build();
    }

}
