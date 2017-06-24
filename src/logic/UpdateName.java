package logic;

import static util.SettingReader.*;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import util.Logger;

public class UpdateName extends Updater{

  public UpdateName() {
    twitter = TwitterFactory.getSingleton();
    tweet = new Tweet();
    logger = new Logger();
  }

  public void updateNameCall(Status status) throws TwitterException, IOException {
    TIME_FOOTER = dtf.format(ZonedDateTime.now());
    Pattern p = Pattern.compile(String.format("@%s update_name (.{1,21})", twitter.getScreenName()));
    Matcher m = p.matcher(status.getText());

    if (m.find()) {
      this.UpdateNameAccess(status, m.group(1), status.getUser().getScreenName());
    }
  }

  private void UpdateNameAccess(Status status, String newName, String screenName) throws TwitterException {
    if (getUpdateNameAccessLevel() == 2) {
      this.updateNameExec(status, newName, screenName);
    } else if (getUpdateNameAccessLevel() == 1) {
      if (screenName.equals(twitter.getScreenName())) {
        this.updateNameExec(status, newName, screenName);
      } else {
        tweet.ReplyTweet(String.format("@%s Permission Dnied" + TIME_FOOTER, screenName), status);
        logger.ouputErrorLog("Name has couldn't changed to " + newName + " (Permission denied)(by " + screenName + ")");
      }
    } else if (getUpdateNameAccessLevel() == 0) {
      tweet.ReplyTweet(String.format("@%s update_namer is now tuned off." + TIME_FOOTER, screenName), status);
      logger.ouputErrorLog("Name has couldn't changed to " + newName + " (Tuned off)(by " + screenName + ")");
    }

  }

  private void updateNameExec(Status status, String newName, String screenName) throws TwitterException {
    try {
      twitter.updateProfile(newName, null, null, null);
      tweet.ReplyTweet("名前を\"" + newName + "\"に変更しました" + TIME_FOOTER + "(by @" + screenName + ")", status);
      logger.ouputLog("Name has changed to \"" + newName + "\" by @" + screenName);
      System.out.println("Name has changed to \"" + newName + "\" by @" + screenName);
    } catch (TwitterException e) {
      if (newName.length() > 20) {
        tweet.ReplyTweet(String.format("@%s ユーザーネームは20文字以内で指定して下さい" + TIME_FOOTER, screenName), status);
        logger.ouputErrorLog("Name has couldn't changed " + newName + " (OutOfBoundsException)(by " + screenName + ")");
      } else if (newName.contains("Twitter")) {
        tweet.ReplyTweet(String.format("@%s \"Twitter\"を含む名前にはできません。" + TIME_FOOTER, screenName), status);
        logger.ouputErrorLog("Name has counldn't changed to " + newName + " (TwitterException)(by " + screenName + ")");
      } else {
        tweet.ReplyTweet(String.format("@%s 不明なエラーが発生しました" + TIME_FOOTER, screenName), status);
        logger.ouputErrorLog("Name has couldn't changed to " + newName + " (Unknown error)(by " + screenName + ")");
      }
      e.printStackTrace();
    }

  }
  
  public void getUpdateNameLevel(Status status) throws IllegalStateException, TwitterException {
    Pattern p = Pattern.compile(String.format("@%s getLvl-update_name", twitter.getScreenName()));
    Matcher m = p.matcher(status.getText());
    
    if (m.find()) {
      tweet.ReplyTweet((String.format("@%s 現在のUpdateNameのアクセスレベルは" + getUpdateNameAccessLevel() +"です.", status.getUser().getScreenName())), status);
    }
  }

}
