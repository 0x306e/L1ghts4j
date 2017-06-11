package debug;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class containsTest {

  public static void main(String[] args) {
    String str = ".@0x306e update_name strrrrrr";
    
    Pattern p = Pattern.compile(String.format(("(@0x306e update_name (.{1,20}))||((.{1,120})@0x306e update_name (.{1,20}))")));
    Matcher m = p.matcher(str);

    if(m.find()){
          System.out.println(m.group(4));

    }
    
    String s = "ああああああああああああああああああああああああああああああ";
    System.out.println(s.length());

  }

}
