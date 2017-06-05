package l1ghts.account;

import java.io.IOException;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class AccountManager {

  public static Configuration getConfig() throws IOException {
    ConfigurationBuilder builder = new ConfigurationBuilder();
    SettingReader settingReader = new SettingReader();

    builder.setDebugEnabled(true);
    builder.setOAuthConsumerKey(settingReader.getConsumerKey());
    builder.setOAuthConsumerSecret(settingReader.getConsumerSecret());
    builder.setOAuthAccessToken(settingReader.getAccessToken());
    builder.setOAuthAccessTokenSecret(settingReader.getAccessTokenSecret());

    Configuration conf = builder.build();
    return conf;
  }

}
