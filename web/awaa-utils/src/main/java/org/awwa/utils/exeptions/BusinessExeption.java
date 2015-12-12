/**
 * 
 */
package org.awwa.utils.exeptions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author John
 *
 */
public class BusinessExeption extends Exception {
	private static final long serialVersionUID = 1L;

	private Logger error = Logger.getGlobal();

	public BusinessExeption() {
		super();
	}

	public BusinessExeption(String message, String sqlState, int vendorCode, Throwable cause) {
		super(message, cause);
		error.log(Level.SEVERE, message, cause);
	}

	public BusinessExeption(String message, Throwable cause) {
		super(message, cause);
		error.log(Level.SEVERE, message, cause);
	}

	public BusinessExeption(String message) {
		super(message);
		error.log(Level.SEVERE, message);
	}

	public BusinessExeption(Throwable cause) {
		super(cause);
		error.log(Level.SEVERE, cause.getMessage(), cause);
	}
}
