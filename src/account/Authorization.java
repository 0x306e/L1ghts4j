package account;

import twitter4j.auth.OAuth2Authorization;
import twitter4j.conf.Configuration;

@SuppressWarnings("serial")
public class Authorization extends OAuth2Authorization {
	Configuration conf;
	
	public Authorization(Configuration config) {
		super(config);
	}
	
}
