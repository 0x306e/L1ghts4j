package logic;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import util.Logger;
import logic.Tweet;

public class UpdateLocation {
  Twitter twitter;
  
  public UpdateLocation() {
    twitter = TwitterFactory.getSingleton();
  }

  public void updateLocation(Status status) throws IllegalStateException, TwitterException, IOException {
    Pattern p = Pattern.compile(String.format("@%s update_location (.{1,31})", twitter.getScreenName()));
    Matcher m = p.matcher(status.getText());
    Tweet t = new Tweet();
    Logger l = new Logger();
    
    if (m.find()) {
      twitter.updateProfile(null, null, m.group(1), null);
      t.ReplyTweet(String.format("位置情報を%sに更新しました。", m.group(1)), status);
      l.ouputLog(String.format("Location has changed to \"%s\" (by @%s", m.group(1), status.getUser().getScreenName()));
      System.out.println((String.format("Location has changed to \"%s\" (by @%s", m.group(1), status.getUser().getScreenName())));
      
    }
  }
}
