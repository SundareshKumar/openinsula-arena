package org.openinsula.arena.appengine.gwt.client.http;

import com.google.gwt.http.client.RequestBuilder;

/**
 * @author Eduardo Rebola
 */
// TODO melhorar essa API. Os metodos nao estao bem definidos
public interface EnhancedRequestBuilderFilter {

	void beforeBuildRequestBuilder(EnhancedRequestBuilder requestBuilder);
	
	RequestBuilder overrideBuiltRequestBuilder(EnhancedRequestBuilder requestBuilder, RequestBuilder builtRequestBuilder); 

}
