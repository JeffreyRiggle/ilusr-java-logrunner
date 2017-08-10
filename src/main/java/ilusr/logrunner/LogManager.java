package ilusr.logrunner;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogManager {

	private final Logger logger;
	private Level level;
	
	public LogManager() {
		logger = Logger.getLogger(LogRunner.class.getName());
	}
	
	public void setLevel(Level newLevel) {
		level = newLevel;
		for (Handler handler : logger.getHandlers()) {
			handler.setLevel(level);
		}
	}
	
	public void addHandler(Handler handler) {
		logger.addHandler(handler);
	}
	
	public void all(String message) {
		logger.log(Level.ALL, message);
	}
	
	public void config(String message) {
		logger.log(Level.CONFIG, message);
	}
	
	public void fine(String message) {
		logger.log(Level.FINE, message);
	}
	
	public void finer(String message) {
		logger.log(Level.FINER, message);
	}
	
	public void finest(String message) {
		logger.log(Level.FINEST, message);
	}
	
	public void info(String message) {
		logger.log(Level.INFO, message);
	}
	
	public void off(String message) {
		logger.log(Level.INFO, message);
	}
	
	public void severe(String message) {
		logger.log(Level.SEVERE, message);
	}
	
	public void severe(Exception exception) {
		StringWriter writer = new StringWriter();
		PrintWriter printer = new PrintWriter(writer);
		exception.printStackTrace(printer);
		logger.log(Level.SEVERE, writer.toString());
		
		try {
			printer.close();
			writer.close();
		} catch (IOException e) {
		}
	}
	
	public void warning(String message) {
		logger.log(Level.WARNING, message);
	}
}
