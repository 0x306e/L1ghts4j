package logic;

import java.io.IOException;

import account.ConfigurationAdapter;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;

public class UserStream extends Thread {
    private TwitterStream twitterStream;
    private Configuration conf;
    private MyUserStreamAdapter userStreamAdapter;

    @SuppressWarnings("unused")
    private UserStream() {
    }
    
    public UserStream(Configuration config) {
    	this.conf = config;
    	this.userStreamAdapter = new MyUserStreamAdapter();
        this.twitterStream = new TwitterStreamFactory(conf).getInstance();
        this.twitterStream.addListener(this.userStreamAdapter);
    }
    
    public static UserStream getInstance(Configuration config) {
    	UserStream userStream = new UserStream(config);
    	return userStream;
    }
    
    public static UserStream getInstance(String id) {
    	Configuration config = null;
    	try {
	        config = ConfigurationAdapter.getConfig(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return getInstance(config);
    }
    
    public void addStatusListener(StatusListener s) {
    	this.userStreamAdapter.addStatusListener(s);
    }

    @Override
    public void run() {
    	twitterStream.user();
    }

}
