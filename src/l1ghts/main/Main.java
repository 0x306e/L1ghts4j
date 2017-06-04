package l1ghts.main;

import l1ghts.logic.UserStream;
import l1ghts.util.Logger;

public class Main
{

  public static void main(String[] args) throws Exception
  {
    Logger log = new Logger();
    log.outputStartLog();
    
    UserStream us = new UserStream();
    us.start();
  }

}
