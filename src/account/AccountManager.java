package account;

import java.io.IOException;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author 0x306e
 * アカウントの呼び出しを管理するクラス
 */
public class AccountManager {

  /**
   * アクセストークン情報をセットするクラス
   * @return conf
   * @throws IOException
   */
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
