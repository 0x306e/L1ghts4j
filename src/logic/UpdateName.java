package logic;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import util.Logger;
import util.SettingReader;

public class UpdateName {

  private Twitter twitter;
  private Tweet tweet;
  private Logger logger;

  private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("(yyyy-MM-dd_HH:mm:ss.SSS)");
  private ZonedDateTime zdt = ZonedDateTime.now();
  private String TIME_FOOTER = dtf.format(zdt);

  public UpdateName() {
    twitter = TwitterFactory.getSingleton();
    tweet = new Tweet();
    logger = new Logger();
  }

  public void updateName(Status status) throws TwitterException, IOException {
    System.out.println("update name has called.");

    Pattern p = Pattern.compile(String.format("@%s update_name (.{1,21})", twitter.getScreenName()));
    Matcher m = p.matcher(status.getText());

    if (m.find()) {
      this.UpdateNameAccess(status, m.group(1), status.getUser().getScreenName());
    }

  }

  private void UpdateNameAccess(Status status, String newName, String screenName) throws TwitterException {
    SettingReader settingReader = new SettingReader();

    if (settingReader.getUpdateNameAccessLevel() == 2) {
      try {
        twitter.updateProfile(newName, null, null, null);
        tweet.ReplyTweet("名前を\"" + newName + "\"に変更しました(by @" + screenName + ")" + TIME_FOOTER, status);
        logger.ouputLog("Name has changed to \"" + newName + "\" by @" + screenName);
        System.out.println("Name has changed to \"" + newName + "\" by @" + screenName);
      } catch (TwitterException e) {
        if (newName.length() > 20) {
          tweet.ReplyTweet("ユーザーネームは20文字以内で指定して下さい" + TIME_FOOTER, status);
          logger
              .ouputErrorLog("Name has couldn't changed " + newName + " (OutOfBoundsException)(by " + screenName + ")");
        } else if (newName.contains("Twitter")) {
          tweet.ReplyTweet("\"Twitter\"を含む名前にはできません。" + TIME_FOOTER, status);
          logger
              .ouputErrorLog("Name has counldn't changed to " + newName + " (TwitterException)(by " + screenName + ")");
        } else {
          tweet.ReplyTweet("不明なエラーが発生しました" + TIME_FOOTER, status);
          logger.ouputErrorLog("Name has couldn't changed to " + newName + " (Unknown error)(by " + screenName + ")");
        }
        e.printStackTrace();
      }

    } else if (settingReader.getUpdateNameAccessLevel() == 1) {
      tweet.ReplyTweet("Permission Dnied" + TIME_FOOTER, status);
      logger.ouputErrorLog("Name has couldn't changed to " + newName + " (Permission denied)(by " + screenName + ")");
    } else if (settingReader.getUpdateNameAccessLevel() == 0) {
      tweet.ReplyTweet("update_namer is now tuned off." + TIME_FOOTER, status);
      logger.ouputErrorLog("Name has couldn't changed to " + newName + " (Tuned off)(by " + screenName + ")");
    }

  }

}
