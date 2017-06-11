package account;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  private static BufferedReader br;

  /**
   * twitter4j.propertiesから指定されたデータを読み出すメソッド
   * @param targetName 読み出すデータの名前
   * @return 読み出したデータ
   */
  private static String Reader(String targetName) {
    String read = "", key = "";
    try {
      br = new BufferedReader(new FileReader(TWITTER4J_PROPERTIES));
    } catch (FileNotFoundException e) {
      System.out.println("twitter4j.properties does not found.");
    }
    Pattern formatTarget = Pattern.compile(String.format("%s=(.{1,50})", targetName));
    Matcher matchTarget;

    int cnt = 0;
    try {
      do {
        read = br.readLine();
        matchTarget = formatTarget.matcher(read);

        if (matchTarget.find()) {
          key = matchTarget.group(1);
        }
        cnt++;
      } while (key == "" && cnt < 5);

      br.close();
    } catch (IOException e) {
      if (key == "") {
        System.out.println("twitter4j.propertiesの読み込み中にエラーが発生しました.");
        System.out.println("API keyの情報が間違っている可能性があります.");
      }
    }

    System.out.println(key);
    return key;

  }

  /**
   * @return Debugのtrue/falseを返すメソッド
   * @throws IOException
   */
  public static boolean getDebugValue() throws IOException {
    boolean value = "true".equals(Reader(DEBUG_VALUE));
    return value;
  }

  /**
   * @return Access Tokenのデータを返すメソッド
   * @throws IOException
   */
  public String getAccessToken() throws IOException {
    return Reader(ACCESS_TOKEN);
  }

  /**
   * @return Access Token Secretのデータを返すメソッド
   * @throws IOException
   */
  public String getAccessTokenSecret() throws IOException {
    return Reader(ACCESS_TOKEN_SECRET);
  }

  /**
   * @return Consumer keyのデータを返すメソッド
   * @throws IOException
   */
  public String getConsumerKey() throws IOException {
    return Reader(CONSUMER_KEY);
  }

  /**
   * @return Consumer Secretのデータを返すメソッド
   * @throws IOException
   */
  public String getConsumerSecret() throws IOException {
    return Reader(CONSUMER_KEY_SECRET);
  }

}
