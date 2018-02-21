package main;

import java.io.IOException;
import java.util.ArrayList;

import account.ConfigurationAdapter;
import io.IDList;
import logic.StatusListener;
import logic.UpdateName;
import logic.UserStream;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import util.Logger;

public class Main {

	public static void main(String[] args) throws Exception {
		Logger log = Logger.getInstance();
		log.outputStartLog();
		IDList idList = new IDList();
		ArrayList<String> ids = idList.load().getIDs();
		ids.forEach(id -> {
			Configuration conf;
			try {
				conf = ConfigurationAdapter.getConfig(id);
				Twitter twitter = new TwitterFactory(conf).getInstance();
				StatusListener un = new UpdateName(twitter);
				UserStream us = UserStream.getInstance(conf);
				us.addStatusListener(un);
				us.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		);
	}

}
