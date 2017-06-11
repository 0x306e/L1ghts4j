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

  public void ConnectUserStream() throws IllegalStateException, TwitterException, IOException {

    Configuration conf = AccountManager.getConfig();
    Twitter twitter = new TwitterFactory(conf).getInstance();
    TwitterStream twitterStream = new TwitterStreamFactory(conf).getInstance();
    UpdateName updateName = new UpdateName();

    twitterStream.addListener(new UserStreamAdapter() {
      public void onStatus(Status status) {
        try {
          updateName.updateName(status);
        } catch (TwitterException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    });

    twitterStream.user();
  }

  @Override
  public void run() {
    try {
      this.ConnectUserStream();
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (TwitterException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
