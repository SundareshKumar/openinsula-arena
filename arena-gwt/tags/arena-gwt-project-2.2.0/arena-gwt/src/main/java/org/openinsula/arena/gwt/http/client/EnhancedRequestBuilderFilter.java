package org.openinsula.arena.gwt.http.client;

import com.google.gwt.http.client.RequestBuilder;

/**
 * @author Eduardo Rebola
 */
// TODO melhorar essa API. Os metodos nao estao bem definidos
public interface EnhancedRequestBuilderFilter {

	void beforeBuildRequestBuilder(EnhancedRequestBuilder requestBuilder);
	
	RequestBuilder overrideBuiltRequestBuilder(EnhancedRequestBuilder requestBuilder, RequestBuilder builtRequestBuilder); 

}
