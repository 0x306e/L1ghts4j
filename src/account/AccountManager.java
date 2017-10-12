package account;

import java.io.IOException;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author 0x306e アカウントの呼び出しを管理するクラス
 */
public class AccountManager {

    /**
     * アクセストークン情報をセットするクラス
     * 
     * @return conf
     * @throws IOException
     */
    public static Configuration getConfig() throws IOException {
        ConfigurationBuilder builder = new ConfigurationBuilder();

        builder.setDebugEnabled(SettingReader.getDebugValue());
        builder.setOAuthConsumerKey(SettingReader.getConsumerKey());
        builder.setOAuthConsumerSecret(SettingReader.getConsumerSecret());
        builder.setOAuthAccessToken(SettingReader.getAccessToken());
        builder.setOAuthAccessTokenSecret(SettingReader.getAccessTokenSecret());

        Configuration conf = builder.build();
        return conf;
    }

}
