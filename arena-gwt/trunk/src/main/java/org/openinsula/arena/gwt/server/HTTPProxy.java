package org.openinsula.arena.gwt.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HTTPProxy extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		URL url = null;
		String urlString = null;
		String user;
		String password;
		String method = "GET";
		String post = null;
		int timeout = 0;
		Set entrySet = req.getParameterMap().entrySet();

		Map headers = new HashMap();

		Map<String, String> query = new HashMap<String, String>();

		for (Iterator iter = entrySet.iterator(); iter.hasNext();) {

			Map.Entry header = (Map.Entry) iter.next();
			String key = (String) header.getKey();
			String value = ((String[]) header.getValue())[0];
			if (key.compareTo("user") == 0) {
				user = value;
			}
			else if (key.compareTo("password") == 0) {
				password = value;
			}
			else if (key.compareTo("timeout") == 0) {
				timeout = Integer.parseInt(value);
			}
			else if (key.compareTo("method") == 0) {
				method = value;
			}
			else if (key.compareTo("post") == 0) {
				post = value;
			}
			else if (key.compareTo("url") == 0) {
				urlString = value;
			}
			else if (key.startsWith("query")) {
				String q = key.substring(5);
				query.put(q, value);
			}
			else {
				headers.put(key, value);
			}
		}

		url = new URL(urlString + buildQuery(query));

		if (url != null) {
			boolean complete = false;
			while (!complete) {

				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setDoInput(true);
				urlConnection.setUseCaches(false);
				urlConnection.setInstanceFollowRedirects(false);
				urlConnection.setRequestMethod(method);
				if (timeout > 0) {
					urlConnection.setConnectTimeout(timeout);
				}

				// set headers
				Set headersSet = headers.entrySet();
				for (Iterator iter = headersSet.iterator(); iter.hasNext();) {
					Map.Entry header = (Map.Entry) iter.next();
					urlConnection.setRequestProperty((String) header.getKey(), (String) header.getValue());
				}

				// send post
				if (post != null) {
					urlConnection.setDoOutput(true);
					OutputStream out = urlConnection.getOutputStream();
					out.write(post.getBytes());
					out.flush();
				}

				// get content type
				String contentType = urlConnection.getContentType();
				if (contentType != null) {
					res.setContentType(contentType);
				}

				// get reponse code
				int responseCode = urlConnection.getResponseCode();

				if (responseCode == 302) {
					String location = urlConnection.getHeaderField("Location");
					url = new URL(location);
				}
				else {
					res.setStatus(responseCode);
					BufferedInputStream in;
					if (responseCode == 200 || responseCode == 201) {
						in = new BufferedInputStream(urlConnection.getInputStream());
					}
					else {
						in = new BufferedInputStream(urlConnection.getErrorStream());
					}

					// send output to client
					BufferedOutputStream out = new BufferedOutputStream(res.getOutputStream());
					int c;
					while ((c = in.read()) >= 0) {
						out.write(c);
					}
					out.flush();
					complete = true;
				}
			}
		}
	}

	public static String buildQuery(Map<String, String> query) {
		StringBuilder postBuilder = new StringBuilder();
		postBuilder.append("?");

		Iterator<Entry<String, String>> iteratorQuery = query.entrySet().iterator();

		while (iteratorQuery.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iteratorQuery.next();

			if (entry.getValue() == null) {
				continue;
			}

			postBuilder.append(entry.getKey());
			postBuilder.append("=");
			postBuilder.append(entry.getValue());

			if (iteratorQuery.hasNext()) {
				postBuilder.append("&");
			}
		}

		return postBuilder.toString();
	}
}