package logic;

import java.util.List;

import twitter4j.Status;
import twitter4j.UserStreamAdapter;

public class MyUserStreamAdapter extends UserStreamAdapter {
	List<StatusListener> listOfStatusListener;
	
	public void addStatusListener(StatusListener statusListener) {
		listOfStatusListener.add(statusListener);
	}
	
	@Override
	public void onStatus(Status status) {
		listOfStatusListener.forEach(s -> s.receiver(status));
	}
	
}
