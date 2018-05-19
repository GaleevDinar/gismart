package ru.javamentor.gismart.executor;

import java.io.IOException;

public class PythonExecutor {
	public static void run() {
		String command = "";
		try {
			Process p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

