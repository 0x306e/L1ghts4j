package logic;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import util.Logger;
import util.SettingReader;

public class UpdateLocation extends TwitterHeader{

  public UpdateLocation() {
    twitter = TwitterFactory.getSingleton();
    tweet = new Tweet();
    logger = new Logger();
  }

  public void updateLocationCall(Status status) throws TwitterException, IOException {
    TIME_HEADER = dtf.format(ZonedDateTime.now());
    System.out.println("update location called.");
    Pattern p = Pattern.compile(String.format("@%s update_location (.{1,31})", twitter.getScreenName()));
    Matcher m = p.matcher(status.getText());

    if (m.find()) {
      this.UpdateLocationAccess(status, m.group(1), status.getUser().getScreenName());
    }
  }

  private void UpdateLocationAccess(Status status, String newLocation, String screenName) throws TwitterException {
    SettingReader settingReader = new SettingReader();

    if (settingReader.getUpdateNameAccessLevel() == 2) {
      this.updateLocationExec(status, newLocation, screenName);
    } else if (settingReader.getUpdateNameAccessLevel() == 1) {
      if (screenName.equals(twitter.getScreenName())) {
        this.updateLocationExec(status, newLocation, screenName);
      } else {
        tweet.ReplyTweet("Permission Dnied" + TIME_HEADER, status);
        logger.ouputErrorLog(
            "Loction has couldn't changed to " + newLocation + " (Permission denied)(by " + screenName + ")");

      }
    } else if (settingReader.getUpdateNameAccessLevel() == 0) {
      tweet.ReplyTweet("update_locationer is now tuned off." + TIME_HEADER, status);
      logger.ouputErrorLog("Location has couldn't changed to " + newLocation + " (Tuned off)(by " + screenName + ")");
    }

  }

  private void updateLocationExec(Status status, String newLocation, String screenName) throws TwitterException {
    try {
      twitter.updateProfile(null, null, newLocation, null);
      tweet.ReplyTweet("名前を\"" + newLocation + "\"に変更しました" + TIME_HEADER + "(by @" + screenName + ")", status);
      logger.ouputLog("Location has changed to \"" + newLocation + "\" by @" + screenName);
      System.out.println("Location has changed to \"" + newLocation + "\" by @" + screenName);
    } catch (TwitterException e) {
      if (newLocation.length() > 30) {
        tweet.ReplyTweet("位置情報は30文字以内で指定して下さい" + TIME_HEADER, status);
        logger.ouputErrorLog(
            "Location has couldn't changed " + newLocation + " (OutOfBoundsException)(by " + screenName + ")");
      } else {
        tweet.ReplyTweet("不明なエラーが発生しました" + TIME_HEADER, status);
        logger.ouputErrorLog(
            "Location has couldn't changed to " + newLocation + " (Unknown error)(by " + screenName + ")");
      }
      e.printStackTrace();
    }

  }

}
