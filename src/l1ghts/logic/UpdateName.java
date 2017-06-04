package l1ghts.logic;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import l1ghts.util.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class UpdateName
{

  public UpdateName()
  {
    System.out.println("UpdateName object has created.");
  }
  
  public void updateName(Status status) throws TwitterException, IOException
  {
    Twitter twitter = TwitterFactory.getSingleton();
    Logger logger = new Logger();
    Tweet tweet = new Tweet();
    
    Pattern updateNameFormat = Pattern.compile(String.format("^@%s update_name (.{1,20})$", twitter.getScreenName()));
    Matcher matchUpdateName = updateNameFormat.matcher(status.getText());
    
    String newName = matchUpdateName.group(1);
    String screenName = status.getUser().getScreenName();
    
    User test = status.getUser();
    
    System.out.println(test);
    System.out.println(screenName);
    
    
    if (matchUpdateName.find())
    {
      System.out.println("trying update name.");

      try
      {

        twitter.updateProfile(newName, null, null, null);
        tweet.ReplyTweet("名前を\"" + newName + "\"に変更しました(by @" + screenName + ")", status);
        logger.ouputUpdateNameLog("Name has changed to \"" + newName + "\" by @" + screenName);
        System.out.println("Name has changed to \"" + newName + "\" by @" + screenName);

      } catch (TwitterException te)
      {
        System.out.println(te);
        te.printStackTrace();
      } 
    }

    
  }
  
//previous ver.
//  public void updateName(Status status, String NewName, String ScreenName) throws IOException, TwitterException
//  {
//
//    Twitter twitter = TwitterFactory.getSingleton();
//    Logger logger = new Logger();
//    
//    System.out.println("trying update name.");
//
//    try
//    {
//
//      twitter.updateProfile(NewName, null, null, null);
//      //tweet.ReplyTweet(String.format("名前を\"" + NewName + "\"に変更しました(by @%s)", ScreenName), status);
//      twitter.updateStatus(new StatusUpdate(String.format("名前を\"" + NewName + "\"に変更しました(by @%s)", ScreenName))
//          .inReplyToStatusId(status.getId()));
//      logger.ouputUpdateNameLog(String.format("Name has changed to \"%s\" by @%s", NewName, ScreenName));
//      System.out.println(String.format("Name has changed to \"%s\" by @%s", NewName, ScreenName));
//
//    } catch (TwitterException e)
//    {
//
//      if (NewName.length() > 20)
//      {
//
//        twitter.updateStatus(new StatusUpdate(String.format("名前を\"" + NewName + "\"に変更しました(by @%s)", ScreenName))
//            .inReplyToStatusId(status.getId()));
//        //tweet.ReplyTweet(String.format("@%s 20文字以上には設定できません", ScreenName), status);
//        logger.ouputErrorLog(String.format(
//            "Tried to change the name to \"%s\" by @%s, but occurred Out of length error.", NewName, ScreenName));
//        System.out.println(String.format("Tried to change the name to \"%s\" by @%s, but occurred Out of length error.",
//            NewName, ScreenName));
//
//      } else if (NewName.indexOf("Twitter") != -1)
//      {
//
//        twitter.updateStatus(new StatusUpdate(String.format("@%s \"Twitter\"を含む名前には設定できません", ScreenName))
//            .inReplyToStatusId(status.getId()));
//        //tweet.ReplyTweet(String.format("@%s \"Twitter\"を含む名前には設定できません", ScreenName), status);
//        logger.ouputErrorLog(
//            String.format("Tried to change the name to \"%s\" by @%s, but can't change to include strings \"Twitter\".",
//                NewName, ScreenName));
//        System.out.println(
//            String.format("Tried to change the name to \"%s\" by @%s, but can't change to include strings \"Twitter\".",
//                NewName, ScreenName));
//
//      } else if (NewName == "")
//      {
//
//        twitter.updateStatus(new StatusUpdate(String.format("@%s 名前を空にはできません", ScreenName))
//            .inReplyToStatusId(status.getId()));
//        //tweet.ReplyTweet(String.format("@%s 名前を空にはできません", ScreenName), status);
//        logger.ouputErrorLog(
//            String.format("Tried to change the name to \"%s\" by @%s, but can't change to null.", NewName, ScreenName));
//        System.out.println(
//            String.format("Tried to change the name to \"%s\" by @%s, but can't change to null.", NewName, ScreenName));
//
//      } else
//      {
//
//        twitter.updateStatus(new StatusUpdate(String.format("@%s 不明なエラーが発生しました", ScreenName))
//            .inReplyToStatusId(status.getId()));
//        //tweet.ReplyTweet(String.format("@%s 不明なエラーが発生しました", ScreenName), status);
//        logger.ouputErrorLog(String.format(
//            "Tried to change the name to \"%s\" by @%s, but occurred unknown error.\n" + e, NewName, ScreenName));
//        System.out.println(String.format("Tried to change the name to \"%s\" by @%s, but occurred unknown error.\n" + e,
//            NewName, ScreenName));
//
//      }
//
//    }
//
//  }

}
