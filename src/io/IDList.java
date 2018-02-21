package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class IDList {
	private ArrayList<String> IDs;
	private static final String file = "IDList.conf";

	public IDList() {
		IDs = new ArrayList<String>();
	}

	public IDList load() {
		IDs.clear();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String str = null;
			do {
				str = br.readLine();
				if (str != null) {
					IDs.add(str);
				}
			} while (str != null);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public void store() {
		if (this.IDs.isEmpty()) {
			return;
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
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
	
	public ArrayList<String> getIDs() {
		return this.IDs;
	}
}
