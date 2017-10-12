package logic;

import static util.SettingReader.getUpdateNameAccessLevel;

import java.io.IOException;
import java.time.ZonedDateTime;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import util.Logger;

public class UpdateLocation extends UpdaterAdaptor {

    public UpdateLocation(Twitter t) {
        twitter = t;
        tweet = new Tweet(t);
        logger = new Logger();
    }

    public void reciver(Status status) throws TwitterException, IOException {
        TIME_HEADER = dtf.format(ZonedDateTime.now());
        boolean rt = status.isRetweet();
        boolean reply = status.getText().contains("@" + twitter.getScreenName());
        boolean call = status.getText().contains("update_location");

        if (!rt && reply && call) {
            String newName = status.getText().substring(status.getText().lastIndexOf("update_location") + 15).trim();
            this.UpdateLocationAccess(status, newName, status.getUser().getScreenName());
        }
    }

    private void UpdateLocationAccess(Status status, String newLocation, String screenName) throws TwitterException {
        if (getUpdateNameAccessLevel() == 2) {
            this.exec(status, newLocation, screenName);
        } else if (getUpdateNameAccessLevel() == 1) {
            if (screenName.equals(twitter.getScreenName())) {
                this.exec(status, newLocation, screenName);
            } else {
                tweet.ReplyTweet("Permission Dnied" + TIME_HEADER, status);
                logger.ouputErrorLog("Loction has couldn't changed to " + newLocation + " (Permission denied)(by "
                        + screenName + ")");

            }
        } else if (getUpdateNameAccessLevel() == 0) {
            tweet.ReplyTweet("update_locationer is now tuned off." + TIME_HEADER, status);
            logger.ouputErrorLog(
                    "Location has couldn't changed to " + newLocation + " (Tuned off)(by " + screenName + ")");
        }

    }

    protected void exec(Status status, String newLocation, String screenName) throws TwitterException {
        try {
            twitter.updateProfile(null, null, newLocation, null);
            tweet.ReplyTweet("名前を\"" + newLocation + "\"に変更しました" + TIME_HEADER + "(by @" + screenName + ")", status);
            logger.ouputLog("Location has changed to \"" + newLocation + "\" by @" + screenName);
            System.out.println("Location has changed to \"" + newLocation + "\" by @" + screenName);
        } catch (TwitterException e) {
            if (newLocation.length() > 30) {
                tweet.ReplyTweet("位置情報は30文字以内で指定して下さい" + TIME_HEADER, status);
                logger.ouputErrorLog("Location has couldn't changed " + newLocation + " (OutOfBoundsException)(by "
                        + screenName + ")");
            } else {
                tweet.ReplyTweet("不明なエラーが発生しました" + TIME_HEADER, status);
                logger.ouputErrorLog(
                        "Location has couldn't changed to " + newLocation + " (Unknown error)(by " + screenName + ")");
            }
            e.printStackTrace();
        }

    }

}
