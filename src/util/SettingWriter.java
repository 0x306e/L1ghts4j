package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SettingWriter {
  private final static String L1GHTS4J_PROPS = "l1ghts4j.properties";

  private final static String UPDATE_NAME_ACCESS_LEVEL = "updateName.accessLevel";
  private final static String UPDATE_LOCATION_ACCESS_LEVEL = "updateLocation.accessLevel";
  private final static String UPDATE_ICON_ACCESS_LEVEL = "updateIcon.accessLevel";

  private static void Writer(String FileName, String targetName, String value) {
    Properties prop = new Properties();
    
    try {
      prop.load(new FileInputStream(FileName));
    } catch (FileNotFoundException e) {
      System.out.println("[ERROR] " + FileName + " does not found.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("[ERROR] IOException has occurred.");
      e.printStackTrace();
    }
    prop.setProperty(targetName, value);
  }

  /**
   * UpdateNameのアクセスレベルをセットするクラス
   */
  public void setUpdateNameAccessLevel(String setValue) {
    Writer(L1GHTS4J_PROPS, UPDATE_NAME_ACCESS_LEVEL, setValue);
  }
  
  /**
   * UpdateLocationのアクセスレベルをセットするクラス
   */
  public void setUpdateLocationAccessLevel(String setValue) {
    Writer(L1GHTS4J_PROPS, UPDATE_LOCATION_ACCESS_LEVEL, setValue);
  }
  
  /**
   * UpdateIconのアクセスレベルをセットするクラス
   */
  public void getUpdateIconAccessLevel(String setValue) {
    Writer(L1GHTS4J_PROPS, UPDATE_ICON_ACCESS_LEVEL, setValue);
  }
}
