package org.openinsula.arena.gwt.json.client;

import java.io.Serializable;

public interface HasJsonTypes extends Serializable {
	
	Class<?>[] getTypes();

}
