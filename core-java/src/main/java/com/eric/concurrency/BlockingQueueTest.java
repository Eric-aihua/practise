package com.eric.concurrency;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) {
		int BLOCK_SIZE = 10;
		int SEARCHS = 100;
		String url = "src/com/eric";
		String keyWord = "implements";
		BlockingQueue<File> bq = new ArrayBlockingQueue<File>(BLOCK_SIZE);
		EunmerationTask et = new EunmerationTask(bq, new File(url));
		Thread t = new Thread(et);
		t.start();
		for (int i = 0; i < SEARCHS; i++) {
			new Thread(new SearchTask(bq, "extends")).start();
		}

	}
}

class EunmerationTask implements Runnable {
	private BlockingQueue<File> fileQueue;
	public static File Dump = new File("");
	private File startingDirectory;

	public EunmerationTask(BlockingQueue<File> fileQueue, File startingDirectory) {
		super();
		this.fileQueue = fileQueue;
		this.startingDirectory = startingDirectory;
	}

	public void emuerate(File file) throws InterruptedException {
		File[] files = file.listFiles();
		for (File file2 : files) {
			if (file2.isDirectory())
				emuerate(file2);
			else {
				fileQueue.put(file2);
			}
		}
	}

	public void run() {
		try {
			emuerate(startingDirectory);
			fileQueue.put(Dump);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SearchTask implements Runnable {
	private BlockingQueue<File> fileQueue;
	private String keyWord;

	public SearchTask(BlockingQueue<File> fileQueue, String keyWord) {
		super();
		this.fileQueue = fileQueue;
		this.keyWord = keyWord;
	}

	public void search(File file) throws FileNotFoundException {
		if (file != EunmerationTask.Dump) {
			Scanner in = new Scanner(new FileInputStream(file));
			int lineNumber = 0;
			while (in.hasNextLine()) {
				lineNumber++;
				String line = in.nextLine();
				if (line.contains(keyWord)) {
					System.out.println(file.getName() + "      " + lineNumber);
					System.out.println(line);
				}
			}
			in.close();
		}
	}

	public void run() {
		boolean done = false;
		while (!done) {
			try {
				File file = fileQueue.take();
				if (file == EunmerationTask.Dump) {
					fileQueue.put(file);
					done = true;
				}
				try {
					search(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
