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
  public static void setUpdateNameAccessLevel(String setValue) {
    if (setValue.equals("0") || setValue.equals("1") || setValue.equals("2")) {
      Writer(L1GHTS4J_PROPS, UPDATE_NAME_ACCESS_LEVEL, setValue);
    } else {
      throw new IllegalArgumentException(String.format("%s is incorrect value.", setValue));
    }
  }

  /**
   * UpdateLocationのアクセスレベルをセットするクラス
   */
  public static void setUpdateLocationAccessLevel(String setValue) {
    if (setValue.equals("0") || setValue.equals("1") || setValue.equals("2")) {
      Writer(L1GHTS4J_PROPS, UPDATE_LOCATION_ACCESS_LEVEL, setValue);
    } else {
      throw new IllegalArgumentException(String.format("%s is incorrect value.", setValue));
    }
  }

  /**
   * UpdateIconのアクセスレベルをセットするクラス
   */
  public static void getUpdateIconAccessLevel(String setValue) {
    if (setValue.equals("0") || setValue.equals("1") || setValue.equals("2")) {
      Writer(L1GHTS4J_PROPS, UPDATE_ICON_ACCESS_LEVEL, setValue);
    } else {
      throw new IllegalArgumentException(String.format("%s is incorrect value.", setValue));
    }
  }
  
}
