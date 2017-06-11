package main;

import logic.UserStream;
import util.Logger;

public class Main {

  public static void main(String[] args) throws Exception {
    Logger log = new Logger();
    log.outputStartLog();

    UserStream us = new UserStream();
    us.start();
  }

}
