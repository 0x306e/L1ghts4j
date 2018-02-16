package logic;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import twitter4j.Twitter;
import util.Logger;

public class TwitterAdapter {
    protected Twitter twitter;
    protected Tweet tweet;
    protected Logger logger;

    protected final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("(yyyy/MM/dd_HH:mm:ss.SSS)");
    protected static String TIME_HEADER;

    public void setTime() {
        TIME_HEADER = dtf.format(ZonedDateTime.now());
    }
}
