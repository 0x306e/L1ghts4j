package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class IDList {
    private static Path path = FileSystems.getDefault().getPath("IDList.conf");
    private static List<String> IDs;


    public static void clear() {
        IDs.clear();
    }

    public static void load() {
        clear();
        try {
            IDs = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void store() {
        if (IDs.isEmpty()) {
            throw new IllegalStateException("ID is not set.");
        }
        try {
            BufferedWriter bw = Files.newBufferedWriter(path);
            IDs.forEach(s -> {
                try {
                    bw.write(s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<String> getIDs() {
        return IDs;
    }

    public static void add(String id) {
        IDs.add(id);
    }
}
