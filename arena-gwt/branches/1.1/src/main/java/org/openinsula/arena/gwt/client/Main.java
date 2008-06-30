package org.openinsula.arena.gwt.client;

import org.openinsula.arena.gwt.client.persistence.GwtEntity;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class Main implements EntryPoint {

	public void onModuleLoad() {

		GwtEntity<Integer> entity = new GwtEntity<Integer>() {

		};

		entity.setId(10);

		RootPanel.get("main").add(new Label(entity.toString()));
	}

}
