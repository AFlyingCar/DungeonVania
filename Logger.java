/*
 * Tyler Robbins
 * 5/18/15
 * Logger
 * Contains static methods for logging functions
 */

import java.io.*;

// TODO: Make this class actually work
public class Logger{
	private static String baseName = "log_";
	private static String filename = baseName;
	private static File file;
	private static PrintWriter out;
	private static boolean canLog;

	public static void log_error(String msg){
		try{
			if(canLog) return;
			msg = "ERROR - " + msg;
			out.println(msg);
			out.flush();
		}catch(Exception e){
			// If this fails, then holy crap is something wrong
			canLog = false;
		}
	}

	public static void log_std(String msg){
		try{
			if(canLog) return;
			msg = "STDOUT - " + msg;
			out.println(msg);
			out.flush();
		}catch(Exception e){
			// I mean it, these exceptions are only here to satisfy the compiler, but I have no idea why this code doesn't work
			canLog = false;
		}
	}

	public static void log_warn(String msg){
		try{
			if(canLog) return;
			msg = "WARN - " + msg;
			out.println(msg);
			out.flush();
		}catch(Exception e){
			// I mean seriously, I checked and createLogFile should work
			canLog = false;
		}
	}

	public static void log_test(String msg){
		try{
			if(canLog) return;
			msg = "TEST - " + msg;
			out.println(msg);
			out.flush();
		}catch(Exception e){
			// Oh well, guess we don't get any log files
			canLog = false;
		}
	}

	private static void createLogFile(){
		try{
			File[] files = new File(".").listFiles();
			int logs = 0;
			for(File f : files)
				if(f.getName().equals(baseName))
					logs++;
			filename += logs + ".txt";
			file = new File(filename);
			if(!file.exists())
				file.createNewFile();
			out = new PrintWriter(file);
			canLog = true;
		}catch(Exception e){
			// You're screwed
			canLog = false;
		}
	}
}
