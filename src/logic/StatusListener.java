package logic;


import twitter4j.Status;

public abstract class StatusListener extends TwitterAdapter {
    public void receiver(Status status) {
    	this.exec(status);
    }
    protected abstract void exec(Status status);

}
