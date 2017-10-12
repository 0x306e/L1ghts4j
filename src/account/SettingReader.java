package account;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author 3sodn twitter4j.propertiesから情報を読み取るクラス
 */
public class SettingReader {

    private static final String TWITTER4J_PROPERTIES = "twitter4j.properties";

    private final static String DEBUG_VALUE = "debug";
    private final static String CONSUMER_KEY = "oauth.consumerKey";
    private final static String CONSUMER_KEY_SECRET = "oauth.consumerSecret";
    private final static String ACCESS_TOKEN = "oauth.accessToken";
    private final static String ACCESS_TOKEN_SECRET = "oauth.accessTokenSecret";

    /**
     * twitter4j.propertiesから指定されたデータを読み出すメソッド
     * 
     * @param targetName
     *            読み出すデータの名前
     * @return 読み出したデータ
     */
    private static String Reader(String fileName, String targetName) {
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] " + fileName + " does not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[ERROR] IOException has occurred.");
            e.printStackTrace();
        }
        String key = prop.getProperty(targetName);

        return key;
    }

    /**
     * @return Debugのtrue/falseを返すメソッド
     * @throws IOException
     */
    public static boolean getDebugValue() throws IOException {
        boolean value = "true".equals(Reader(TWITTER4J_PROPERTIES, DEBUG_VALUE));
        return value;
    }

    /**
     * @return Access Tokenのデータを返すメソッド
     * @throws IOException
     */
    public static String getAccessToken() throws IOException {
        return Reader(TWITTER4J_PROPERTIES, ACCESS_TOKEN);
    }

    /**
     * @return Access Token Secretのデータを返すメソッド
     * @throws IOException
     */
    public static String getAccessTokenSecret() throws IOException {
        return Reader(TWITTER4J_PROPERTIES, ACCESS_TOKEN_SECRET);
    }

    /**
     * @return Consumer keyのデータを返すメソッド
     * @throws IOException
     */
    public static String getConsumerKey() throws IOException {
        return Reader(TWITTER4J_PROPERTIES, CONSUMER_KEY);
    }

    /**
     * @return Consumer Secretのデータを返すメソッド
     * @throws IOException
     */
    public static String getConsumerSecret() throws IOException {
        return Reader(TWITTER4J_PROPERTIES, CONSUMER_KEY_SECRET);
    }

}
