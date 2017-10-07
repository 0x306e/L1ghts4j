package logic;


import twitter4j.Status;
import twitter4j.TwitterException;

public abstract class Updater extends TwitterHeader {
    public abstract void reciever(Status status) throws TwitterException;
    protected abstract void exec(Status status, String newName) throws TwitterException;

}
