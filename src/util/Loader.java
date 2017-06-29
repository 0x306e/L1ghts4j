package util;

import java.io.IOException;

import logic.AccessLevelController;
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
  AccessLevelController alc;

  public Loader() {
    un = new UpdateName();
    ul = new UpdateLocation();
    alc = new AccessLevelController();
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
    ul.updateLocationCall(status);
    alc.relayAccessLevelController(status);
  }

}
