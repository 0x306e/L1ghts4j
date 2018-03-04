package main;

import logic.UserStream;
import twitter4j.conf.Configuration;

public class UserStreamRunner {
    public static void run(Configuration conf) {
        UserStream us = new UserStream(conf);
        us.run();
    }

}
