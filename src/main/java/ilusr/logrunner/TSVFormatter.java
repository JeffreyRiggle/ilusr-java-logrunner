package ilusr.logrunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * 
 * @author Jeff Riggle
 *
 * @summary A logging formatter that stores logs in a tab separated file.
 */
public class TSVFormatter extends Formatter{

	private final String NEW_LINE_REG = "\n";
	private final String NEW_LINE_PLACEHOLDER = "[newline]";
	private boolean first = true;
	
	@Override
	public String format(LogRecord record) {
		String retVal = new String();
		
		if (first) {
			retVal = "Time\tThread\tMessage\tMethod\tClass\tLevel\t\r\n";
			first = false;
		}
		
		Date date = new Date();
		SimpleDateFormat now = new SimpleDateFormat("HH:mm:ss.SSS");
		String time = now.format(date);
		String threadName = Thread.currentThread().getName();
		retVal += String.format("%s\t%s\t%s\t%s\t%s\t%s\t\r\n", time, threadName, stripNewLines(record.getMessage()), record.getSourceMethodName(), record.getSourceClassName(), record.getLevel());
		return retVal;
	}
	
	private String stripNewLines(String message) {
		return message.replaceAll(NEW_LINE_REG, NEW_LINE_PLACEHOLDER);
	}
}
