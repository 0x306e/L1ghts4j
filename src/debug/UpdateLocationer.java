package debug;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import account.AccountManager;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserStreamAdapter;

public class UpdateLocationer {

  public static void main(String[] args) throws IOException, IllegalStateException, TwitterException {
    Twitter twitter = new TwitterFactory(AccountManager.getConfig()).getInstance();
    TwitterStream twitterStream = new TwitterStreamFactory(AccountManager.getConfig()).getInstance();
    
    Pattern p = Pattern.compile(String.format("@%s update_location (.{1,31})", twitter.getScreenName()));
    
    twitterStream.addListener(new UserStreamAdapter() {
      public void onStatus(Status status) {
        Matcher m = p.matcher(status.getText());

        if (m.find()) {
          try {
            twitter.updateProfile(null, null, m.group(1), null);
          } catch (TwitterException e) {
            e.printStackTrace();
          }
          
        }
      }
    });
    twitterStream.user();
  }

}
