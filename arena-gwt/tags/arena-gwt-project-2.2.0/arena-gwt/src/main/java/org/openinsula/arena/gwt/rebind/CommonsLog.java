package org.openinsula.arena.gwt.rebind;

import com.google.gwt.core.ext.TreeLogger;

public class CommonsLog extends TreeLogger {

	private final TreeLogger delegate;

	public CommonsLog(final TreeLogger delegate) {
		super();
		this.delegate = delegate;
	}
	
	// Predicate Methods
	
	public boolean isDebugEnabled() {
		return isLoggable(Type.DEBUG);
	}
	
	public boolean isInfoEnabled() {
		return isLoggable(Type.INFO);
	}
	
	public boolean isWarnEnabled() {
		return isLoggable(Type.WARN);
	}
	
	public boolean isErrorEnabled() {
		return isLoggable(Type.ERROR);
	}
	
	public boolean isSpamEnabled() {
		return isLoggable(Type.SPAM);
	}
	
	public boolean isTraceEnabled() {
		return isLoggable(Type.TRACE);
	}
	
	// Log methods
	
	public void debug(final String format, final Object... args) {
		debug(null, null, format, args);
	}
	
	public void debug(final Throwable caught, final HelpInfo helpInfo, final String format, final Object... args) {
		log(Type.DEBUG, String.format(format, args), null, null);
	}
	
	public void info(final String format, final Object... args) {
		info(null, null, format, args);
	}
	
	public void info(final Throwable caught, final HelpInfo helpInfo, final String format, final Object... args) {
		log(Type.INFO, String.format(format, args), null, null);
	}
	
	public void warn(final String format, final Object... args) {
		warn(null, null, format, args);
	}
	
	public void warn(final Throwable caught, final HelpInfo helpInfo, final String format, final Object... args) {
		log(Type.WARN, String.format(format, args), null, null);
	}
	
	public void error(final String format, final Object... args) {
		error(null, null, format, args);
	}
	
	public void error(final Throwable caught, final HelpInfo helpInfo, final String format, final Object... args) {
		log(Type.ERROR, String.format(format, args), null, null);
	}
	
	public void spam(final String format, final Object... args) {
		spam(null, null, format, args);
	}
	
	public void spam(final Throwable caught, final HelpInfo helpInfo, final String format, final Object... args) {
		log(Type.SPAM, String.format(format, args), null, null);
	}
	
	public void trace(final String format, final Object... args) {
		trace(null, null, format, args);
	}
	
	public void trace(final Throwable caught, final HelpInfo helpInfo, final String format, final Object... args) {
		log(Type.TRACE, String.format(format, args), null, null);
	}
	
	// Delegate methods

	public TreeLogger branch(final Type type, final String msg, final Throwable caught, final HelpInfo helpInfo) {
		return delegate.branch(type, msg, caught, helpInfo);
	}

	public boolean equals(final Object obj) {
		return delegate.equals(obj);
	}

	public int hashCode() {
		return delegate.hashCode();
	}

	public boolean isLoggable(final Type type) {
		return delegate.isLoggable(type);
	}

	public void log(final Type type, final String msg, final Throwable caught, final HelpInfo helpInfo) {
		delegate.log(type, msg, caught, helpInfo);
	}

	public String toString() {
		return delegate.toString();
	}

}
