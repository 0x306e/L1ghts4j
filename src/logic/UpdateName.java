package logic;

import io.PropertyReader;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import util.Logger;

public class UpdateName extends StatusListener {
	String PREFIX = "update_name";
	State state;

	private class State {
		private Status status;
		private String sender;
		private String newName;

		public State(Status s) {
			status = s;
			sender = status.getUser().getScreenName();
			newName = status.getText().substring(status.getText().lastIndexOf(PREFIX) + PREFIX.length()).trim();
		}
	}

	@SuppressWarnings("unused")
	private UpdateName() {
	}

	public UpdateName(Twitter t) {
		twitter = t;
		tweet = new Tweet(t);
		logger = Logger.getInstance();
	}

	protected void exec(Status status) {
		if (status.getText().contains("update_name")) {
			state = new State(status);
		}
		try {
			twitter.updateProfile(this.state.newName, null, null, null);
			this.reportSuccess();
		} catch (TwitterException e) {
			this.reportFailed();
			e.printStackTrace();
		}
	}

	protected boolean getPermission() {
		PropertyReader prop = new PropertyReader();
		prop.load();
		Boolean enabled = Boolean.getBoolean(prop.getValue("updateName.enabled"));
		Boolean publicity = Boolean.getBoolean(prop.getValue("updateName.public"));
		if (enabled && publicity) {
			return true;
		}
		return false;
	}

	protected void reportSuccess() throws TwitterException {
		try {
			tweet.ReplyTweet(
					"名前を\"" + this.state.newName + "\"に変更しました" + TIME_HEADER + "(by @" + this.state.sender + ")",
					this.state.status);
			logger.output("Name has changed to \"" + this.state.newName + "\" by @" + this.state.sender);
			System.out.println("Name has changed to \"" + this.state.newName + "\" by @" + this.state.sender);
		} catch (TwitterException e) {
			e.printStackTrace();
		}

	}

	private void reportFailed() {
		try {
			if (this.state.newName.length() > 20) {
				tweet.ReplyTweet(String.format("@%s ユーザーネームは20文字以内で指定して下さい" + TIME_HEADER, this.state.sender),
						this.state.status);
				logger.ouputErrorLog("Name has couldn't changed " + this.state.newName + " (OutOfBoundsException)(by "
						+ this.state.sender + ")");
			} else if (this.state.newName.contains("Twitter")) {
				tweet.ReplyTweet(String.format("@%s \"Twitter\"を含む名前にはできません。" + TIME_HEADER, this.state.sender),
						this.state.status);
				logger.ouputErrorLog("Name has counldn't changed to " + this.state.newName + " (TwitterException)(by "
						+ this.state.sender + ")");
			} else {
				tweet.ReplyTweet(String.format("@%s 不明なエラーが発生しました" + TIME_HEADER, this.state.sender),
						this.state.status);
				logger.ouputErrorLog("Name has couldn't changed to " + this.state.newName + " (Unknown error)(by "
						+ this.state.sender + ")");
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}

	}

}
