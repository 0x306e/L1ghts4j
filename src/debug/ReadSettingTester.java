package debug;

import static account.SettingReader.getAccessToken;
import static account.SettingReader.getAccessTokenSecret;
import static account.SettingReader.getConsumerKey;
import static account.SettingReader.getConsumerSecret;

import java.io.IOException;

import twitter4j.TwitterException;

public class ReadSettingTester {

  public static void main(String[] args) throws IOException, IllegalStateException, TwitterException {

    System.out.println(getAccessToken());
    System.out.println(getAccessTokenSecret());
    System.out.println(getConsumerKey());
    System.out.println(getConsumerSecret());

  }

}
