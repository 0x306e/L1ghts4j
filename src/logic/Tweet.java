package logic;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * ツイートに関する処理のクラス
 * @author 0x306e
 */
public class Tweet {
  
  Twitter twitter;

  public Tweet() {
    twitter = TwitterFactory.getSingleton();
  }

  /**
   * 通常のツイートを送信するメソッド
   * @param message ツイートするテキスト
   * @throws TwitterException
   */
  public void NormalTweet(String message) throws TwitterException {
    twitter.updateStatus(message);
  }

  /**
   * 特定のツイートにリプライを送るメソッド
   * @param message ツイートするテキスト
   * @param id 送信先のStatus id
   * @throws TwitterException
   */
  public void ReplyTweet(String message, long id) throws TwitterException {
    twitter.updateStatus(new StatusUpdate(message).inReplyToStatusId(id));
  }

  /**
   * 特定のツイートにリプライを送るメソッド
   * @param message ツイートするテキスト
   * @param status 送信先のStatus
   * @throws TwitterException
   */
  public void ReplyTweet(String message, Status status) throws TwitterException {
    twitter.updateStatus(new StatusUpdate(message).inReplyToStatusId(status.getId()));
  }

}
