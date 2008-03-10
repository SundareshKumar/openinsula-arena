package org.openinsula.arena.lang.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility class for try/catch substitution. If any exception occurs, 
 * doCatch is called which, by default, returns null.
 * 
 * @author Eduardo R Danielli
 * @param <R> Return type
 */
public abstract class FailSafeOperation<R> {

	protected static final Log logger = LogFactory.getLog(FailSafeOperation.class);

	private boolean rethrowException;
	
	public FailSafeOperation() {
		this(false);
	}
	
	public FailSafeOperation(final boolean rethrowException) {
		this.rethrowException = rethrowException;
	}
	
	public R doTry() {
		try {
			return tryBody();
		}
		catch (Throwable throwable) {
			return doCatch(throwable);
		}
	}

	protected R doCatch(final Throwable throwable) {
		if (logger.isWarnEnabled()) {
			logger.warn("Exception occured", throwable);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("Action: " + (rethrowException ? "throw RuntimeException" : "return null"));
		}
		
		if (rethrowException) {
			throw (RuntimeException) throwable;
		}
		
		return null;
	}

	protected abstract R tryBody() throws Throwable;
	
}
