package ilusr.logrunner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO: add purging.
/**
 * 
 * @author Jeff Riggle
 *
 */
public class LogRunner {

	private static final String FILE_PATH = System.getProperty("user.home") + "/nilrem/Logging/";
	private static final String EXTENSION = ".tsv";
	private static final String COUNT_REG = "(#\\d+)*.tsv";
	private static String applicationName = "gameLog.tsv";
	private static Level level = Level.FINEST;
	private static boolean initialized;
	private static Logger logger = Logger.getLogger(LogRunner.class.getName());
	private static FileHandler fileHandler;
	private static String currentLogFile;
	private static int iter;
	
	/**
	 * Sets up logging this method must be called before tracing any data.
	 */
	public static void initalize() {
		if (initialized) return;
		
		try {
			String today = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
			String path = FILE_PATH + today;
			
			File dir = new File(path);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}
			
			currentLogFile = generateLogFile(path + "/" + applicationName);
			fileHandler = new FileHandler(currentLogFile);
			fileHandler.setFormatter(new TSVFormatter());
			logger.addHandler(fileHandler);
			setLoggingLevel(level);
			logger.log(Level.OFF, String.format("Starting logging on %s, for %s", today, applicationName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialized = true;
	}
	
	/**
	 * 
	 * @return The Shared Logger instance.
	 */
	public static Logger logger() {
		if (!initialized) {
			initalize();
		}
		
		return logger;
	}
	
	/**
	 * 
	 * @param level The new logging level to trace at.
	 */
	public static void setLoggingLevel(Level newLevel) {
		level = newLevel;
		for (Handler handler : logger.getHandlers()) {
			handler.setLevel(level);
		}
	}
	
	/**
	 * 
	 * @param name The name of the application that is tracing. This determines the name of the log file.
	 */
	public static void setApplicationName(String name) {
		applicationName = name;
	}
	
	/**
	 * 
	 * @return The current application name.
	 */
	public static String getApplicationName() {
		return applicationName;
	}
	
	private static String generateLogFile(String path) {
		String newPath = path + EXTENSION;
		File file = new File(newPath);
		
		if (file.exists()) {
			iter++;
			newPath = newPath.replaceAll(COUNT_REG, "");
			return generateLogFile(newPath + "#" + iter);
		}
		
		return newPath;
	}
	
	public static String getCurrentLogFile() {
		return currentLogFile;
	}
}
