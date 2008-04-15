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
package org.openinsula.arena.lang.util;

import org.apache.commons.logging.Log;

/**
 * @author Eduardo Rebola
 * 
 */
public abstract class LogUtil {

	public static void debug(final Log logger, final String format, final Object... args) {
		if (logger.isDebugEnabled()) {
			logger.debug(getMessage(format, args));
		}
	}
	
	public static void debug(final Log logger, final Throwable throwable, final String format, final Object... args) {
		if (logger.isDebugEnabled()) {
			logger.debug(getMessage(format, args), throwable);
		}
	}

	public static void info(final Log logger, final String format, final Object... args) {
		if (logger.isInfoEnabled()) {
			logger.info(getMessage(format, args));
		}
	}
	
	public static void info(final Log logger, final Throwable throwable, final String format, final Object... args) {
		if (logger.isInfoEnabled()) {
			logger.info(getMessage(format, args), throwable);
		}
	}

	public static void warn(final Log logger, final String format, final Object... args) {
		if (logger.isWarnEnabled()) {
			logger.warn(getMessage(format, args));
		}
	}
	
	public static void warn(final Log logger, final Throwable throwable, final String format, final Object... args) {
		if (logger.isWarnEnabled()) {
			logger.warn(getMessage(format, args), throwable);
		}
	}

	public static void error(final Log logger, final String format, final Object... args) {
		if (logger.isErrorEnabled()) {
			logger.error(getMessage(format, args));
		}
	}
	
	public static void error(final Log logger, final Throwable throwable, final String format, final Object... args) {
		if (logger.isErrorEnabled()) {
			logger.error(getMessage(format, args), throwable);
		}
	}

	public static void fatal(final Log logger, final String format, final Object... args) {
		if (logger.isFatalEnabled()) {
			logger.fatal(getMessage(format, args));
		}
	}
	
	public static void fatal(final Log logger, final Throwable throwable, final String format, final Object... args) {
		if (logger.isFatalEnabled()) {
			logger.fatal(getMessage(format, args), throwable);
		}
	}

	private static String getMessage(final String format, final Object... args) {
		if (args != null && args.length > 0) {
			return String.format(format, args);
		}

		return format;
	}

}
