package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Logger {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS(xxxxx)");
    private static String fs = System.getProperty("file.separator");
    private static String OUTPUT_FILE;

    private Logger() {
    }
    
    public static Logger getInstance() {
    	return SingletonHolder.singleton;
    }
    
    private static class SingletonHolder {
    	private static Logger singleton = new Logger();
    }

    public void setFilePath() {
    	Logger.OUTPUT_FILE = "log" + fs + sdf.format(new Date()) + "-L1ghts4j.log";
    }

    public String getHeader() {
        return "[" + dtf.format(ZonedDateTime.now()) + "]";
    }

    public void output(String message) {
    	this.setFilePath();
        PrintWriter pw;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter((OUTPUT_FILE), true)));
            pw.println(getHeader() + "[INFO]  " + message);
            pw.close();
        } catch (IOException e) {
            System.out.println("[ERROR] IOException has occurred in Log outputment.");
            e.printStackTrace();
        }
    }
    
    public void outputStartLog() {
    	this.output(getHeader() + "[INFO]  L1ghts4j has started.");
    }

    public void ouputErrorLog(String message) {
    	this.output(getHeader() + "[ERROR] " + message);
    }

}
