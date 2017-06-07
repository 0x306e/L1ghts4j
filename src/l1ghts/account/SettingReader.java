package l1ghts.account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 3sodn
 * twitter4j.propertiesから情報を読み取るクラス
 */
public class SettingReader {
  
  private String DEBUG_VALUE = "debug";
  private String CONSUMER_KEY = "oauth.consumerKey";
  private String CONSUMER_KEY_SECRET = "oauth.consumerSecret";
  private String ACCESS_TOKEN = "oauth.accessToken";
  private String ACCESS_TOKEN_SECRET = "oauth.accessTokenSecret";

  private String Reader(String targetName) throws IOException {
    String read = "", key = "";
    BufferedReader br = new BufferedReader(new FileReader("twitter4j.properties"));
    Pattern formatTarget = Pattern.compile(String.format("%s=(.{1,50})", targetName));
    Matcher matchTarget;
    
    do {
      read = br.readLine();
      System.out.println("got str is " + read);
      matchTarget = formatTarget.matcher(read);

      if (matchTarget.find()) {
        key = matchTarget.group(1);
      }
    } while (key == "");

    br.close();
    
    System.out.println(key);
    return key;

  }

  /**
   * @return Debugのtrue/falseを返すメソッド
   * @throws IOException
   */
  public boolean getDebugValue() throws IOException
  {
    boolean value = "true".equals(this.Reader(DEBUG_VALUE));
    return value;
  }
  
  /**
   * @return　Access Tokenのデータを返すメソッド
   * @throws IOException
   */
  public String getAccessToken() throws IOException {
    String at = this.Reader(ACCESS_TOKEN);
    return at;
  }

  /**
   * @return Access Token Secretのデータを返すメソッド
   * @throws IOException
   */
  public String getAccessTokenSecret() throws IOException {
    String as = this.Reader(ACCESS_TOKEN_SECRET);
    return as;
  }

  /**
   * @return Consumer keyのデータを返すメソッド
   * @throws IOException
   */
  public String getConsumerKey() throws IOException {
    String ck = this.Reader(CONSUMER_KEY);
    return ck;
  }

  /**
   * @return Consumer Secretのデータを返すメソッド
   * @throws IOException
   */
  public String getConsumerSecret() throws IOException {
    String cs = this.Reader(CONSUMER_KEY_SECRET);
    return cs;
  }

}
