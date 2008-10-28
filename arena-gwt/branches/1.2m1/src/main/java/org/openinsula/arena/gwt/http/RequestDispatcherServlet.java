package org.openinsula.arena.gwt.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		final HttpURLConnection urlConnection = doDispatch(request);

		final String contentType = urlConnection.getContentType();
		if (contentType != null) {
			response.setContentType(contentType);
		}

		final int responseCode = urlConnection.getResponseCode();

		response.setStatus(responseCode);

		BufferedInputStream in;
		if (responseCode == 200 || responseCode == 201) {
			in = new BufferedInputStream(urlConnection.getInputStream());
		}
		else {
			in = new BufferedInputStream(urlConnection.getErrorStream());
		}

		final BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int c;
		while ((c = in.read()) >= 0) {
			out.write(c);
		}
		out.flush();
	}

	@SuppressWarnings("unchecked")
	private HttpURLConnection doDispatch(ServletRequest request) throws IOException {
		final Map<String, String[]> parameterMap = request.getParameterMap();
		final URL url = createURL(parameterMap);
		final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setInstanceFollowRedirects(false);
		urlConnection.setRequestMethod(request.getParameter("method"));

		final String user = request.getParameter("user");
		if (user != null) {
			urlConnection.addRequestProperty("user", user);
		}

		final String password = request.getParameter("password");
		if (password != null) {
			urlConnection.addRequestProperty("password", password);
		}

		final String timeoutString = request.getParameter("timeout");
		final int timeout = timeoutString == null || timeoutString.trim().isEmpty() ? 0 : Integer
				.parseInt(timeoutString);
		if (timeout > 0) {
			urlConnection.setConnectTimeout(timeout);
		}

		setRequestProperties(urlConnection, parameterMap);

		final String requestData = request.getParameter("requestData");
		if (requestData != null) {
			urlConnection.setDoOutput(true);

			final OutputStreamWriter outRemote = new OutputStreamWriter(urlConnection.getOutputStream());

			outRemote.write(requestData);
			outRemote.flush();
		}

		return urlConnection;
	}

	private void setRequestProperties(URLConnection connection, Map<String, String[]> parameterMap) {
		final Set<String> params = new HashSet<String>();

		params.add("user");
		params.add("password");
		params.add("timeout");
		params.add("method");
		params.add("requestData");
		params.add("url");

		for (final Entry<String, String[]> entry : parameterMap.entrySet()) {
			final String key = entry.getKey();

			if (!(params.contains(key) || key.startsWith("query"))) {

				connection.setRequestProperty(key, entry.getValue()[0]);
			}
		}
	}

	private URL createURL(Map<String, String[]> parameterMap) throws MalformedURLException {
		final StringBuilder sb = new StringBuilder();

		sb.append(parameterMap.get("url")[0]);
		sb.append("?");

		final Iterator<Entry<String, String[]>> queryIterator = parameterMap.entrySet().iterator();

		while (queryIterator.hasNext()) {
			final Map.Entry<String, String[]> entry = queryIterator.next();
			final String key = entry.getKey();
			final String value = entry.getValue()[0];

			if (key.startsWith("query") && value != null) {
				sb.append(key.substring(5));
				sb.append("=");
				sb.append(value);

				if (queryIterator.hasNext()) {
					sb.append("&");
				}
			}
		}

		return new URL(sb.toString());
	}

}
