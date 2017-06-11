package debug;

import java.io.IOException;

import account.AccountManager;
import account.SettingReader;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class ReadSettingTester {

  public static void main(String[] args) throws IOException, IllegalStateException, TwitterException {
    SettingReader settingReader = new SettingReader();

    System.out.println(settingReader.getAccessToken());
    System.out.println(settingReader.getAccessTokenSecret());
    System.out.println(settingReader.getConsumerKey());
    System.out.println(settingReader.getConsumerSecret());

    Twitter twitter = new TwitterFactory(AccountManager.getConfig()).getInstance();

    System.out.println(twitter.getScreenName());
    System.out.println();

  }

}
