package account;

import java.io.IOException;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class AccountManager {

    public static Configuration getConfig(String id) throws IOException {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        SettingReader settingReader = new SettingReader(id);

        builder.setDebugEnabled(settingReader.getDebugValue());
        builder.setOAuthConsumerKey(settingReader.getConsumerKey());
        builder.setOAuthConsumerSecret(settingReader.getConsumerSecret());
        builder.setOAuthAccessToken(settingReader.getAccessToken());
        builder.setOAuthAccessTokenSecret(settingReader.getAccessTokenSecret());

        Configuration conf = builder.build();
        return conf;
    }

}
