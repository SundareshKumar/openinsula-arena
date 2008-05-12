/*
 *  (C) Copyright 2008 Insula Tecnologia da Informacao Ltda.
 *
 *  This file is part of Arena-Lang.
 *
 *  Arena-Lang is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena-Lang is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena-Lang.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.lang;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility class for try/catch substitution. If any exception occurs, doCatch is
 * called which, by default, returns null.
 *
 * @author Eduardo R Danielli
 * @param <R>
 *            Return type
 */
public abstract class FailSafeOperation<R> {

	protected final Log logger = LogFactory.getLog(FailSafeOperation.class);

	private boolean rethrowException;

	/**
	 * By default, <code>null</code> is returned if any exception occurs
	 */
	public FailSafeOperation() {
		this(false);
	}

	/**
	 * @param rethrowException if <b>true</b>, the exception (if occurs) will be rethrowed
	 * as <code>RuntimeException</code>. Otherwise,
	 * <code>null</code> is returned and the exception is ignored.
	 */
	public FailSafeOperation(final boolean rethrowException) {
		this.rethrowException = rethrowException;
	}

	public final R execute() {
		try {
			return operation();
		} catch (Throwable throwable) {
			return handleException(throwable);
		}
	}

	protected abstract R operation() throws Throwable;

	protected R handleException(final Throwable throwable) {
		if (logger.isWarnEnabled()) {
			logger.warn("Exception occured", throwable);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Action: %s", (rethrowException ? "throw RuntimeException" : "return null")));
		}

		if (rethrowException) {
			throw (RuntimeException) throwable;
		}

		return null;
	}

}
