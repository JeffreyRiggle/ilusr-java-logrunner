package ilusr.logrunner;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 
 * @author Jeff Riggle
 *
 */
public class LogManager {

	private final Logger logger;
	private Level level;
	
	public LogManager() {
		logger = Logger.getLogger(LogRunner.class.getName());
	}
	
	/**
	*
	* Sets the logging level.
	* @param newLevel The new level to use for logging.
	*/
	public void setLevel(Level newLevel) {
		level = newLevel;
		logger.setLevel(level);
		for (Handler handler : logger.getHandlers()) {
			handler.setLevel(level);
		}
	}
	
	/**
	*
	* Adds another logging handler.
	* @param handler The handler to add.
	*/
	public void addHandler(Handler handler) {
		logger.addHandler(handler);
	}
	
	/**
	*
	* Logs a message the at the ALL level.
	* @param message The message to log.
	*/
	public void all(String message) {
		logger.log(createRecord(Level.ALL, message));
	}
	
	/**
	*
	* Logs a message the at the CONFIG level.
	* @param message The message to log.
	*/
	public void config(String message) {
		logger.log(createRecord(Level.CONFIG, message));
	}
	
	/**
	*
	* Logs a message the at the FINE level.
	* @param message The message to log.
	*/
	public void fine(String message) {
		logger.log(createRecord(Level.FINE, message));
	}
	
	/**
	*
	* Logs a message the at the FINER level.
	* @param message The message to log.
	*/
	public void finer(String message) {
		logger.log(createRecord(Level.FINER, message));
	}
	
	/**
	*
	* Logs a message the at the FINEST level.
	* @param message The message to log.
	*/
	public void finest(String message) {
		logger.log(createRecord(Level.FINEST, message));
	}
	
	/**
	*
	* Logs a message the at the INFO level.
	* @param message The message to log.
	*/
	public void info(String message) {
		logger.log(createRecord(Level.INFO, message));
	}
	
	/**
	*
	* Logs a message the at the INFO level.
	* @param message The message to log.
	*/
	public void off(String message) {
		logger.log(createRecord(Level.INFO, message));
	}
	
	/**
	*
	* Logs a message the at the SEVERE level.
	* @param message The message to log.
	*/
	public void severe(String message) {
		logger.log(createRecord(Level.SEVERE, message));
	}
	
	/**
	*
	* Logs a message the at the SEVERE level.
	* @param exception The exception to log.
	*/
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
	
	/**
	*
	* Logs a message the at the WARNING level.
	* @param message The message to log.
	*/
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
