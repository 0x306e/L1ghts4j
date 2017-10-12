package logic;

import java.io.IOException;

import account.AccountManager;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserStreamAdapter;
import twitter4j.conf.Configuration;

public class UserStream extends Thread {
    Twitter twitter;
    TwitterStream twitterStream;
    UserStreamAdapter usAdapter;

    public UserStream() throws IOException {
        Configuration conf = AccountManager.getConfig();
        Twitter twitter = new TwitterFactory(conf).getInstance();
        TwitterStream twitterStream = new TwitterStreamFactory(conf).getInstance();

        UpdateName un = new UpdateName(twitter);
        UpdateLocation ul = new UpdateLocation(twitter);

        usAdapter = new UserStreamAdapter() {
            public void onStatus(Status status) {
                try {
                    un.reciever(status);
                    ul.reciever(status);
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
            };
        };
    }

    public void ConnectUserStream() throws IllegalStateException, TwitterException, IOException {
        twitterStream.addListener(usAdapter);
        twitterStream.user();
    }

    @Override
    public void run() {
        try {
            ConnectUserStream();
        } catch (IllegalStateException | TwitterException | IOException e) {
            e.printStackTrace();
        }
    }

}
