package org.awwa.utils.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Logs {
	private static final Logger info = LoggerFactory.getLogger(Logs.class);
	private static final Logger error = LoggerFactory.getLogger(Logs.class);

	public static void logError(String message, Exception e) throws Exception {
		error.debug(message, e);
		e.printStackTrace();
		throw new Exception(message);
	}

	/**
	 * @return the info
	 */
	public static Logger getInfo() {
		return info;
	}

	/**
	 * @return the error
	 */
	public static Logger getError() {
		return error;
	}

}
