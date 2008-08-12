package org.openinsula.arena.gwt.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HTTPProxy extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		URL url = null;
		String user, password, method = "GET", post = null;

		int timeout = 0;
		Set entrySet = req.getParameterMap().entrySet();
		Map headers = new HashMap();

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
				url = new URL(value);
			}
			else {
				headers.put(key, value);
			}
		}

		if (url != null) {
			boolean complete = false;
			while (!complete) {

				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setDoOutput(true);
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
					OutputStreamWriter outRemote = new OutputStreamWriter(urlConnection.getOutputStream());
					outRemote.write(post);
					outRemote.flush();
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
}