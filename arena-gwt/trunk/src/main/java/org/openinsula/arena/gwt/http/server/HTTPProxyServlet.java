package org.openinsula.arena.gwt.http.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HTTPProxyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String URL = "_url";

	private static final String METHOD = "_method";

	private static final String REQUEST_DATA = "_requestData";

	private static final String TIMEOUT = "_timeout";

	private static final String USER = "_user";

	private static final String PASSWORD = "_password";

	private static final String HEADER = "_header";

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
	IOException {

		final HttpURLConnection connection = dispatchRequest(req);
		dispatchResponse(resp, connection);
	}

	@SuppressWarnings("unchecked")
	private HttpURLConnection dispatchRequest(final HttpServletRequest req) throws IOException {
		final Map<String, String[]> parameterMap = new HashMap<String, String[]>();
		parameterMap.putAll(req.getParameterMap());

		final String[] data = parameterMap.remove(REQUEST_DATA);

		final String requestData = data == null ? null : data[0];

		final HttpURLConnection urlConnection = openConnection(parameterMap);

		writeOutput(urlConnection, requestData);

		return urlConnection;
	}

	// Request

	private HttpURLConnection openConnection(final Map<String, String[]> parameterMap) throws IOException {
		String param = parameterMap.remove(URL)[0];
		final URL url = new URL(param);

		final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		param = parameterMap.remove(METHOD)[0];
		urlConnection.setRequestMethod(param);

		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setInstanceFollowRedirects(false);

		param = parameterMap.remove(TIMEOUT)[0];

		try {
			urlConnection.setConnectTimeout(Integer.parseInt(param));
		}
		catch (final RuntimeException e) {
			urlConnection.setConnectTimeout(0);
		}

		writeRequestProperties(urlConnection, parameterMap);

		return urlConnection;
	}

	private void writeRequestProperties(final HttpURLConnection urlConnection, final Map<String, String[]> parameterMap) {
		String[] param = parameterMap.remove(USER);
		if (param != null && param[0] != null) {
			urlConnection.addRequestProperty(USER, param[0]);
		}

		param = parameterMap.remove(PASSWORD);
		if (param != null && param[0] != null) {
			urlConnection.addRequestProperty(PASSWORD, param[0]);
		}

		for (final Entry<String, String[]> entry : parameterMap.entrySet()) {
			String sKey = entry.getKey();
			final String[] sValue = entry.getValue();

			if (sKey.startsWith(HEADER)) {
				sKey = sKey.substring(7);
			}

			urlConnection.setRequestProperty(sKey, sValue[0]);
		}
	}

	private void writeOutput(final HttpURLConnection urlConnection, final String requestData) throws IOException {
		if (requestData != null) {
			urlConnection.setDoOutput(true);

			final OutputStreamWriter output = new OutputStreamWriter(urlConnection.getOutputStream());
			output.write(requestData);
			output.flush();
		}
	}

	// Response

	private void dispatchResponse(final HttpServletResponse response, final HttpURLConnection urlConnection)
	throws IOException {

		final String contentType = urlConnection.getContentType();
		if (contentType != null) {
			response.setContentType(contentType);
		}

		final int responseCode = urlConnection.getResponseCode();
		response.setStatus(responseCode);

		InputStream input = urlConnection.getErrorStream();

		if (input == null) {
			input = urlConnection.getInputStream();
		}

		writeResponseOutput(input, response.getOutputStream());
	}

	private void writeResponseOutput(final InputStream input, final ServletOutputStream output) throws IOException {
		final BufferedInputStream bufferedInput = new BufferedInputStream(input);
		final BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);

		final byte[] buffer = new byte[1024];
		int byteCount = 0;

		while ((byteCount = bufferedInput.read(buffer)) > -1) {
			bufferedOutput.write(buffer, 0, byteCount);
		}

		bufferedOutput.close();
	}

}
