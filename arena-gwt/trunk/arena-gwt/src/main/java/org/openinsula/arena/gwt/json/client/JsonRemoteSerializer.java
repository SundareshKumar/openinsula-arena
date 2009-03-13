package org.openinsula.arena.gwt.json.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("JsonRemoteSerializer")
public interface JsonRemoteSerializer extends RemoteService {

	String toJson(Serializable jsonObject);

	<T extends Serializable> T fromJson(String jsonString, T template);
	
}
