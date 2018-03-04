package main;

import java.io.IOException;
import java.util.List;

import account.ConfigurationAdapter;
import io.IDList;
import twitter4j.conf.Configuration;
import util.Logger;

public class Main {

    public static void main(String[] args) throws Exception {
        Logger log = Logger.getInstance();
        log.outputStartLog();
        for(String arg: args) {
            if(arg.equals("run")) {
                run();
            }
            if(arg.equals("add")) {
                add();
            }
        }

    }
    
    public static void run() {
        IDList.load();
        List<String> ids = IDList.getIDs();
        ids.forEach(id -> {
            Configuration conf;
            try {
                conf = ConfigurationAdapter.getConfig(id);
                UserStreamRunner.run(conf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        );
 
    }
    
    public static void add() {
        try {
            AddAccount.addAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
