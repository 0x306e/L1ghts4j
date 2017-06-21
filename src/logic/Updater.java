package logic;

import java.time.format.DateTimeFormatter;

import twitter4j.Twitter;
import util.Logger;

public class Updater {
  protected Twitter twitter;
  protected Tweet tweet;
  protected Logger logger;

  protected final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("(yyyy/MM/dd_HH:mm:ss.SSS)");
  protected static String TIME_FOOTER;

}
