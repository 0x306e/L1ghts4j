package main;

import java.io.IOException;

import account.ConfigurationAdapter;
import twitter4j.TwitterException;
import twitter4j.auth.OAuth2Authorization;
import twitter4j.conf.Configuration;

public class AddAccount {

	public static void main(String[] args) {
		Configuration conf = null;
		OAuth2Authorization oauth = null;
		try {
			conf = ConfigurationAdapter.getConsumer();
		} catch (IOException e) {
			e.printStackTrace();
		}
  		oauth = new OAuth2Authorization(conf);
		if (oauth != null) try {
			oauth.getOAuth2Token();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
