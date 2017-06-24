package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author 0x306e
 * l1ghts4j.propertiesから情報を読み出すメソッドのクラス
 */
public class SettingReader {

  private final static String L1GHTS4J_PROPS = "l1ghts4j.properties";

  private final static String UPDATE_NAME_ACCESS_LEVEL = "updateName.accessLevel";
  private final static String UPDATE_LOCATION_ACCESS_LEVEL = "updateLocation.accessLevel";
  private final static String UPDATE_ICON_ACCESS_LEVEL = "updateIcon.accessLevel";

  private static String Reader(String FileName, String targetName) {
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
    String value = prop.getProperty(targetName);
    
    return value;
  }

  /**
   * UpdateNameのアクセスレベルを返すクラス
   * @return int 0~2
   */
  public static int getUpdateNameAccessLevel() {
    return Integer.parseInt(Reader(L1GHTS4J_PROPS, UPDATE_NAME_ACCESS_LEVEL));
  }
  
  /**
   * UpdateLocationのアクセスレベルを返すクラス
   * @return int 0~2
   */
  public static int getUpdateLocationAccessLevel() {
    return Integer.parseInt(Reader(L1GHTS4J_PROPS, UPDATE_LOCATION_ACCESS_LEVEL));
  }
  
  /**
   * UpdateIconのアクセスレベルを返すクラス
   * @return int 0~2
   */
  public static int getUpdateIconAccessLevel() {
    return Integer.parseInt(Reader(L1GHTS4J_PROPS, UPDATE_ICON_ACCESS_LEVEL));
  }

}
