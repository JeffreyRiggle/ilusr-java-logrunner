package ilusr.logrunner;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogManager {

	private final Logger logger;
	private Level level;
	
	public LogManager() {
		logger = Logger.getLogger(LogRunner.class.getName());
	}
	
	public void setLevel(Level newLevel) {
		level = newLevel;
		logger.setLevel(level);
		for (Handler handler : logger.getHandlers()) {
			handler.setLevel(level);
		}
	}
	
	public void addHandler(Handler handler) {
		logger.addHandler(handler);
	}
	
	public void all(String message) {
		logger.log(createRecord(Level.ALL, message));
	}
	
	public void config(String message) {
		logger.log(createRecord(Level.CONFIG, message));
	}
	
	public void fine(String message) {
		logger.log(createRecord(Level.FINE, message));
	}
	
	public void finer(String message) {
		logger.log(createRecord(Level.FINER, message));
	}
	
	public void finest(String message) {
		logger.log(createRecord(Level.FINEST, message));
	}
	
	public void info(String message) {
		logger.log(createRecord(Level.INFO, message));
	}
	
	public void off(String message) {
		logger.log(createRecord(Level.INFO, message));
	}
	
	public void severe(String message) {
		logger.log(createRecord(Level.SEVERE, message));
	}
	
	public void severe(Exception exception) {
		StringWriter writer = new StringWriter();
		PrintWriter printer = new PrintWriter(writer);
		exception.printStackTrace(printer);
		logger.log(createRecord(Level.SEVERE, writer.toString()));
		
		try {
			printer.close();
			writer.close();
		} catch (IOException e) {
		}
	}
	
	public void warning(String message) {
		logger.log(createRecord(Level.WARNING, message));
	}
	
	private LogRecord createRecord(Level level, String message) {
		LogRecord record = new LogRecord(level, message);
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		StackTraceElement element = trace[3];
		record.setSourceMethodName(element.getMethodName());
		record.setSourceClassName(element.getClassName());
		return record;
	}
}
