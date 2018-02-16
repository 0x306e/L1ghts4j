package logic;


import twitter4j.Status;
import twitter4j.TwitterException;

public abstract class StatusListener extends TwitterAdapter {
    public void receiver(Status status) {
    	this.exec(status);
    }
    protected abstract void exec(Status status);

}
