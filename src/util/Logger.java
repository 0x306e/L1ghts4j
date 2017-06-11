package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 3sodn ログ出力をするクラス
 */
public class Logger {

  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
  private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS(xxxxx)");

  private Date date = new Date();
  private ZonedDateTime zdt = ZonedDateTime.now();
  private File file = new File("log");

  private String OUTPUT_DIRECTORY = "log\\" + sdf.format(date) + "-L1ghts4j.log";
  private String LOG_HEADER = "[" + dtf.format(zdt) + "]";

  public Logger() {
    if (!file.exists())
      file.mkdirs();
  }

  /**
   * 開始時のログを出力するメソッド
   * 
   * @throws Exception
   */
  public void outputStartLog() {
    try {
      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter((OUTPUT_DIRECTORY), true)));
      pw.println(LOG_HEADER + "[INFO]  L1ghts4j has started.");
      pw.close();
    } catch (IOException e) {
      System.out.println("[ERROR] IOException has occurred in start log outputment.");
      e.printStackTrace();
    }
  }

  /**
   * 標準的なログを出力するメソッド
   * 
   * @param message
   *          出力するログメッセージ
   * @throws IOException
   */
  public void ouputLog(String message) {
    PrintWriter pw;
    try {
      pw = new PrintWriter(new BufferedWriter(new FileWriter((OUTPUT_DIRECTORY), true)));
      pw.println(LOG_HEADER + "[INFO]  " + message);
      pw.close();
    } catch (IOException e) {
      System.out.println("[ERROR] IOException has occurred in Log outputment.");
      e.printStackTrace();
    }
  }

  /**
   * エラーログを出力するメソッド
   * 
   * @param message
   *          出力するログメッセージ
   * @throws IOException
   */
  public void ouputErrorLog(String message) {
    try {
      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter((OUTPUT_DIRECTORY), true)));
      pw.println(LOG_HEADER + "[ERROR] " + message);
      pw.close();
    } catch (Exception e) {
      System.out.println("[ERROR] IOException has occurred in Error log outputment.");
      e.printStackTrace();
    }
  }

}
