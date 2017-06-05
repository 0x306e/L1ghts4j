package l1ghts.logic;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Tweet {

  Twitter twitter;

  public Tweet() {
    twitter = TwitterFactory.getSingleton();
  }

  public void NormalTweet(String Message) throws TwitterException {
    twitter.updateStatus(Message);
  }

  public void ReplyTweet(String Message, long id) throws TwitterException {
    twitter.updateStatus(new StatusUpdate(Message).inReplyToStatusId(id));
  }

  public void ReplyTweet(String Message, Status status) throws TwitterException {
    twitter.updateStatus(new StatusUpdate(Message).inReplyToStatusId(status.getId()));
  }

}
