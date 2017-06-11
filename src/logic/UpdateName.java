package logic;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import util.Logger;

public class UpdateName {

  Twitter twitter;

  public UpdateName() {
    twitter = TwitterFactory.getSingleton();
  }

  public void updateName(Status status) throws TwitterException, IOException {
    System.out.println("update name has called.");

    Logger logger = new Logger();
    Tweet tweet = new Tweet();

    Pattern updateNameFormat = Pattern.compile(String.format("@%s update_name (.{1,20})", twitter.getScreenName()));
    Matcher matchUpdateName = updateNameFormat.matcher(status.getText());

    String screenName = status.getUser().getScreenName();

    if (matchUpdateName.find()) {
      String newName = matchUpdateName.group(1);

      try {
        twitter.updateProfile(newName, null, null, null);
        tweet.ReplyTweet("名前を\"" + newName + "\"に変更しました(by @" + screenName + ")", status);
        logger.ouputLog("Name has changed to \"" + newName + "\" by @" + screenName);
        System.out.println("Name has changed to \"" + newName + "\" by @" + screenName);

      } catch (TwitterException te) {
        te.printStackTrace();
      }
    }
  }

}
