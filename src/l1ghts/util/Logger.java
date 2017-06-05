package l1ghts.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Logger {
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
  private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS(xxxxx)");

  private Date date = new Date();
  private ZonedDateTime zdt = ZonedDateTime.now();

  private File file = new File("log");

  public Logger() {
    if (file.exists() == false)
      file.mkdirs();
  }

  public void outputStartLog() throws Exception {
    PrintWriter pw = new PrintWriter(
        new BufferedWriter(new FileWriter(("log\\" + sdf.format(date) + "-L1ghts4j.log"), true)));
    pw.println("[" + dtf.format(zdt) + "][INFO]  L1ghts4j has started.");
    pw.close();
  }

  public void ouputUpdateNameLog(String message) throws IOException {
    PrintWriter pw = new PrintWriter(
        new BufferedWriter(new FileWriter(("log\\" + sdf.format(date) + "-L1ghts4j.log"), true)));
    pw.println("[" + dtf.format(zdt) + "][INFO]  " + message);
    pw.close();
  }

  public void ouputErrorLog(String message) throws IOException {
    PrintWriter pw = new PrintWriter(
        new BufferedWriter(new FileWriter(("log\\" + sdf.format(date) + "-L1ghts4j.log"), true)));
    pw.println("[" + dtf.format(zdt) + "][ERROR] " + message);
    pw.close();
  }

}
