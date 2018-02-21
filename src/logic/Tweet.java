package logic;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Tweet {

    Twitter twitter;

    public Tweet(Twitter t) {
        twitter = t;
    }

    public void NormalTweet(String message) throws TwitterException {
        twitter.updateStatus(message);
    }

    public void ReplyTweet(String message, long id) throws TwitterException {
        twitter.updateStatus(new StatusUpdate(message).inReplyToStatusId(id));
    }

    public void ReplyTweet(String message, Status status) throws TwitterException {
        twitter.updateStatus(new StatusUpdate(message).inReplyToStatusId(status.getId()));
    }

}
