/*
 * Tyler Robbins
 * 5/18/15
 * Logger
 * Contains methods for logging functions
 */

import java.io.*;

// Ignore all of the other comments here, I figured out a way that "should" work

// So, it turns out that the reason Logger never worked was because i forgot to call createLogFile
// I'm so sorry...

// TODO: Make this class actually work
public class Logger{
	private static Logger _instance;

	private String baseName = "log_";
	private String filename = baseName;
	private File file;
	private PrintWriter out;
	private boolean canLog;

	// Singleton constructor
	private Logger(){
		canLog = false; // Disabled Logger because it doesn't like me.
		// createLogFile();
	}

	public void log_error(String msg){
		try{
			if(!canLog) return;
			msg = "ERROR - " + msg;
			out.println(msg);
			out.flush();
		}catch(Exception e){
			// If this fails, then holy crap is something wrong
			canLog = false;
		}
	}

	public void log_error(Throwable t){
		try{
			if(!canLog) return;
			t.printStackTrace(out);
		}catch(Exception e){
			// To make sure these comments look right, I'll just leave this here
			System.out.println("An error ocurred!");
			// System.out.println(e);
			// e.printStackTrace();
			canLog = false;
		}
	}

	public void log_std(String msg){
		try{
			if(!canLog) return;
			msg = "STDOUT - " + msg;
			out.println(msg);
			out.flush();
		}catch(Exception e){
			// I mean it, these exceptions are only here to satisfy the compiler, but I have no idea why this code doesn't work
			canLog = false;
		}
	}

	public void log_warn(String msg){
		try{
			if(!canLog) return;
			msg = "WARN - " + msg;
			out.println(msg);
			out.flush();
		}catch(Exception e){
			// I mean seriously, I checked and createLogFile should work
			canLog = false;
		}
	}

	public void log_test(String msg){
		try{
			if(!canLog) return;
			msg = "TEST - " + msg;
			out.println(msg);
			out.flush();
		}catch(Exception e){
			// Oh well, guess we don't get any log files
			canLog = false;
		}
	}

	private void createLogFile(){
		try{
			File[] files = new File(".").listFiles();
			int logs = 0;
			for(File f : files)
				if(f.getName().startsWith(baseName))
					logs++;
			filename += logs + ".txt";
			file = new File(filename);
			if(!file.exists())
				file.createNewFile();
			out = new PrintWriter(file);
			canLog = true;
		}catch(Exception e){
			// Sorry, but if this fails to work, I can't help you...
			canLog = false;
		}
	}

	public static Logger getInstance(){
		if(_instance == null)
			_instance = new Logger();
		return _instance;
	}
}
