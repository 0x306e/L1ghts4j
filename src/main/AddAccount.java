package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.IDList;
import io.PropertyReader;
import io.PropertyWriter;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class AddAccount {
    static String command = "start";
    static String ck_key = "default.consumerKey";
    static String cs_key = "default.consumerSecret";
    static String at_key = ".accessToken";
    static String as_key = ".accessTokenSecret";

    public static void addAccount() throws Exception {
        PropertyReader pr = new PropertyReader();
        String ck = pr.getValue(ck_key);
        String cs = pr.getValue(cs_key);
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(ck, cs);
        RequestToken rt = twitter.getOAuthRequestToken();
        String url = rt.getAuthorizationURL();

        System.out.println("Authorization.");
        System.out.println(url);
        Runtime.getRuntime().exec(command + url);
        System.out.print("PIN CODE : ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pin = br.readLine();
        AccessToken at = twitter.getOAuthAccessToken(pin);
        
        PropertyWriter pw = new PropertyWriter();
        String uid = String.valueOf(at.getUserId());
        pw.setValue(uid + at_key, at.getToken());
        pw.setValue(uid + as_key, at.getTokenSecret());
        pw.store();
        IDList.add(uid);
        
        System.out.println("Successful!");
    }

}
