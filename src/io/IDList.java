package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class IDList {
	private Path path = FileSystems.getDefault().getPath("IDList.conf");
	private List<String> IDs;

	public IDList() {
		IDs = new ArrayList<String>();
	}
	
	public void clear() {
		this.IDs.clear();
	}

	public IDList load() {
		this.clear();
		try {
			this.IDs = Files.readAllLines(path);
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
	
	public List<String> getIDs() {
		return this.IDs;
	}
}
