package org.openinsula.blackberry.textdatabase.connection.configuration;

public class HttpConfiguration extends ConnectorConfiguration {

	private String url;

	private String httpMethod;

	public HttpConfiguration(String url, String httpMethod) {
		this.url = url;
		this.httpMethod = httpMethod;
	}

	public String getUrl() {
		return url;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

}
