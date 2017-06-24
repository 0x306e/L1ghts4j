package util;

import java.io.IOException;

import logic.UpdateIcon;
import logic.UpdateLocation;
import logic.UpdateName;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * @author 0x306e 方向性を決め損ねたクラス あとで作り直す可能性が微レ存
 */
public class Loader {

  UpdateName un;
  UpdateLocation ul;
  UpdateIcon ui;

  public Loader() {
    un = new UpdateName();
    ul = new UpdateLocation();
  }

  /**
   * UserStreamから受け取ったStatusを中継するメソッド
   * 
   * @param status
   * @throws TwitterException
   * @throws IOException
   */
  public void UserStreamReceicever(Status status) throws TwitterException, IOException {
    un.updateNameCall(status);
    un.getUpdateNameLevel(status);
    ul.updateLocationCall(status);
  }

}
