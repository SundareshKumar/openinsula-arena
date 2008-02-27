package org.openinsula.arena.io.textfile.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.line.LineFactory;

public class InputStreamParseContext implements ParseContext {
	protected final Log logger = LogFactory.getLog(getClass());

	protected int lineCount = 0;

	protected Map<Class<? extends LineFactory>, Method> parseMap;

	protected Object handler;

	protected BufferedReader br;

	protected String nextLine;

	public InputStreamParseContext(InputStream in, Charset charset, Map<Class<? extends LineFactory>, Method> parseMap,
			Object handler) {
		this.parseMap = parseMap;
		this.handler = handler;
		br = new BufferedReader(new InputStreamReader(in, charset));
	}

	public String getLine() throws IOException {
		lineCount++;
		if (nextLine == null) {
			return br.readLine();
		}
		else {
			String s = nextLine;
			nextLine = null;
			return s;
		}
	}

	public String lookAhead() throws IOException {
		if (nextLine == null) {
			nextLine = br.readLine();
		}
		return nextLine;
	}

	public void process(Line line) {
		Method method = parseMap.get(line.getLineFactory().getClass());
		if (method != null) {
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("Invoking method \"" + method.getName() + "\" to process line of "
							+ line.getLineFactory().getClass().getSimpleName());
				}
				method.invoke(handler, new Object[] { line });
			}
			catch (Exception ex) {
				logger.warn("Exception thrown invoking method \'" + method.getName() + "\' of class "
						+ handler.getClass().getSimpleName(), ex);
				logger.warn("Cause of exception : " + ex.getCause());
				throw new IllegalArgumentException(ex);
			}
		}
		else {
			if (logger.isWarnEnabled()) {
				logger.warn("No method found to process line of " + line.getLineFactory().getClass().getSimpleName());
			}
		}
	}

	public int getCurrentLineCount() {
		return lineCount;
	}
}
