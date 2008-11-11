package org.openinsula.arena.gwt.samples.client;

import org.openinsula.arena.gwt.http.client.RemoteRequestBuilder;
import org.openinsula.arena.gwt.http.client.RequestBuilder;
import org.openinsula.arena.gwt.http.client.RequestBuilderFactory;
import org.openinsula.arena.gwt.http.client.rest.BasicRestService;
import org.openinsula.arena.gwt.http.client.rest.BeanRestService;
import org.openinsula.arena.gwt.http.client.rest.RestService;

public class Services {

	private static Services instance;

	private final RequestBuilderFactory requestBuilderFactory;

	private final RestService defaultRestService;

	private BeanRestService beanRestService;

	private Services() {
		requestBuilderFactory = new RequestBuilderFactory() {
			public RequestBuilder createRequestBuilder() {
				return new RemoteRequestBuilder();
			}
		};

		defaultRestService = new BasicRestService(requestBuilderFactory);
	}

	public static Services getInstance() {
		if (instance == null) {
			instance = new Services();
		}
		return instance;
	}

	public BeanRestService getBeanService() {
		if (beanRestService == null) {
			beanRestService = new BeanRestService(defaultRestService);
		}
		return beanRestService;
	}
	
}
