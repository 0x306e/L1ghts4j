package l1ghts.account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SettingReader
{

  private String Reader(String targetName) throws IOException
  {
    String read = "", key = "";
    BufferedReader br = new BufferedReader(new FileReader("AccountSetting.txt"));
    Pattern formatTarget = Pattern.compile(String.format("%s=(.{1,50})", targetName));
    Matcher matchTarget;
    
    do
    {
      read = br.readLine();
      System.out.println("got str is "+read);
      matchTarget = formatTarget.matcher(read);
      
      if (matchTarget.find())
      {
        key = matchTarget.group(1);
      }
      
    } while (key == "");
    
    br.close();
    return key;
    
  }

  public String getAccessToken() throws IOException
  {
    String at = this.Reader("AccessToken");
    return at;
  }

  public String getAccessTokenSecret() throws IOException
  {
    String as = this.Reader("AccessTokenSecret");
    return as;
  }

  public String getConsumerKey() throws IOException
  {
    String ck = this.Reader("ConsumerKey");
    return ck;
  }

  public String getConsumerSecret() throws IOException
  {
    String cs = this.Reader("ConsumerSecret");
    return cs;
  }

}
