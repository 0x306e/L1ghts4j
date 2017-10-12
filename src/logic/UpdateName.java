package logic;

import static util.SettingReader.*;

import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import util.Logger;

public class UpdateName extends Updater {

    @SuppressWarnings("unused")
    private UpdateName() {
    }

    public UpdateName(Twitter t) {
        twitter = t;
        tweet = new Tweet(t);
        logger = new Logger();
    }

    public void reciever(Status status) throws TwitterException {
        TIME_HEADER = dtf.format(ZonedDateTime.now());
        boolean rt = status.isRetweet();
        boolean reply = status.getText().contains(twitter.getScreenName());
        boolean call = status.getText().contains("update_name");

        if (!rt && reply && call) {
            String newName = status.getText().substring(status.getText().lastIndexOf("update_name") + 12).trim();
            this.accesser(status, newName, status.getUser().getScreenName());
        }
    }

    private void accesser(Status status, String newName, String screenName) throws TwitterException {
        int lvl = getUpdateNameAccessLevel();
        if (lvl == 2) {
            this.exec(status, newName);
        } else if (lvl == 1) {
            if (screenName.equals(twitter.getScreenName())) {
                this.exec(status, newName);
            } else {
                tweet.ReplyTweet(String.format("@%s Permission Dnied" + TIME_HEADER, screenName), status);
                logger.ouputErrorLog(
                        "Name has couldn't changed to " + newName + " (Permission denied)(by " + screenName + ")");
            }
        } else if (lvl == 0) {
            tweet.ReplyTweet(String.format("@%s update_namer is now tuned off." + TIME_HEADER, screenName), status);
            logger.ouputErrorLog("Name has couldn't changed to " + newName + " (Tuned off)(by " + screenName + ")");
        }

    }

    protected void exec(Status status, String newName) throws TwitterException {
        String screenName = status.getUser().getScreenName();
        try {
            twitter.updateProfile(newName, null, null, null);
            tweet.ReplyTweet("名前を\"" + newName + "\"に変更しました" + TIME_HEADER + "(by @" + screenName + ")", status);
            logger.ouputLog("Name has changed to \"" + newName + "\" by @" + screenName);
            System.out.println("Name has changed to \"" + newName + "\" by @" + screenName);
        } catch (TwitterException e) {
            if (newName.length() > 20) {
                tweet.ReplyTweet(String.format("@%s ユーザーネームは20文字以内で指定して下さい" + TIME_HEADER, screenName), status);
                logger.ouputErrorLog(
                        "Name has couldn't changed " + newName + " (OutOfBoundsException)(by " + screenName + ")");
            } else if (newName.contains("Twitter")) {
                tweet.ReplyTweet(String.format("@%s \"Twitter\"を含む名前にはできません。" + TIME_HEADER, screenName), status);
                logger.ouputErrorLog(
                        "Name has counldn't changed to " + newName + " (TwitterException)(by " + screenName + ")");
            } else {
                tweet.ReplyTweet(String.format("@%s 不明なエラーが発生しました" + TIME_HEADER, screenName), status);
                logger.ouputErrorLog(
                        "Name has couldn't changed to " + newName + " (Unknown error)(by " + screenName + ")");
            }
            e.printStackTrace();
        }

    }

    public void getUpdateNameLevel(Status status) throws IllegalStateException, TwitterException {
        Pattern p = Pattern.compile(String.format("@%s getLvl-update_name", twitter.getScreenName()));
        Matcher m = p.matcher(status.getText());

        if (m.find()) {
            tweet.ReplyTweet((String.format("@%s 現在のUpdateNameのアクセスレベルは" + getUpdateNameAccessLevel() + "です.",
                    status.getUser().getScreenName())), status);
        }
    }

}
