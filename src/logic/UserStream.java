package logic;

import java.io.IOException;

import account.AccountManager;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserStreamAdapter;
import twitter4j.conf.Configuration;

public class UserStream extends Thread {
  UpdateName un = new UpdateName();
  UserStreamAdapter usAdapter;

  public UserStream() {
    usAdapter = new UserStreamAdapter() {
      public void onStatus(Status status) {
        try {
          un.updateNameCall(status);
        } catch (TwitterException | IOException e) {
          e.printStackTrace();
        }
      };
    };
  }

  public void ConnectUserStream() throws IllegalStateException, TwitterException, IOException {

    Configuration conf = AccountManager.getConfig();
    Twitter twitter = new TwitterFactory(conf).getInstance();
    TwitterStream twitterStream = new TwitterStreamFactory(conf).getInstance();

    twitterStream.addListener(usAdapter);
    twitterStream.user();
  }

  @Override
  public void run() {
    try {
      ConnectUserStream();
    } catch (IllegalStateException | TwitterException | IOException e) {
      e.printStackTrace();
    }
  }

}
