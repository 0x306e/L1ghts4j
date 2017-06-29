package logic;

import static util.SettingReader.*;
import static util.SettingWriter.*;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class AccessLevelController extends TwitterHeader {
  private String state;

  public AccessLevelController() {
    twitter = TwitterFactory.getSingleton();
    tweet = new Tweet();
  }

  private void setState(int i) {
    if (i == 0) {
      state = "0(turned off)";
    } else if (i == 1) {
      state = "1(private)";
    } else if (i == 2) {
      state = "2(public)";
    }
  }
  
  public void relayAccessLevelController(Status status) throws IllegalStateException, TwitterException {
    this.getUpdateNameLevel(status);
    this.getUpdateLocationLevel(status);
    this.setUpdateNameLevel(status);
    this.setUpdateLocationLevel(status);
  }

  public boolean getUpdateNameLevel(Status status) throws IllegalStateException, TwitterException {
    super.setTimeHeader();

    if (status.getText().contains(twitter.getScreenName()) && status.getText().contains("get-level update_name")) {
      this.setState(getUpdateNameAccessLevel());
      tweet.ReplyTweet(String.format("@%s 現在の状態は" + state + "です。" + TIME_HEADER), status);

      return true;

    } else {
      return false;
    }
  }

  public void setUpdateNameLevel(Status status) throws IllegalStateException, TwitterException {
    super.setTimeHeader();

    if (status.getText().contains(twitter.getScreenName()) && status.getText().contains("set-level update_name")) {
      if (!twitter.getScreenName().equals(status.getUser().getScreenName())) {
        try {
          setUpdateNameAccessLevel(status.getText().substring(status.getText().indexOf("update_name") + 11).trim());
        } catch (IllegalArgumentException e) {
          tweet.ReplyTweet(String.format("@%s 値は0~2で指定してください。" + TIME_HEADER, status.getUser().getScreenName()),
              status);
          e.printStackTrace();
        }
      } else {
        tweet.ReplyTweet(String.format("@%s Permission denied." + TIME_HEADER, status.getUser().getScreenName()),
            status);
      }
    }
  }

  public boolean getUpdateLocationLevel(Status status) throws IllegalStateException, TwitterException {
    super.setTimeHeader();

    if (status.getText().contains(twitter.getScreenName()) && status.getText().contains("get-level update_location")) {
      this.setState(getUpdateLocationAccessLevel());
      tweet.ReplyTweet(String.format("@%s 現在の状態は" + state + "です。" + TIME_HEADER), status);

      return true;

    } else {
      return false;
    }

  }

  public void setUpdateLocationLevel(Status status) throws IllegalStateException, TwitterException {
    super.setTimeHeader();

    if (status.getText().contains(twitter.getScreenName()) && status.getText().contains("set-level update_location")) {
      if (!twitter.getScreenName().equals(status.getUser().getScreenName())) {
        try {
          setUpdateLocationAccessLevel(status.getText().substring(status.getText().indexOf("update_name") + 11).trim());
        } catch (IllegalArgumentException e) {
          tweet.ReplyTweet(String.format("@%s 値は0~2で指定してください。" + TIME_HEADER, status.getUser().getScreenName()),
              status);

          e.printStackTrace();
        }
      } else {
        tweet.ReplyTweet(String.format("@%s Permission denied." + TIME_HEADER, status.getUser().getScreenName()),
            status);
      }
    }

  }
}
