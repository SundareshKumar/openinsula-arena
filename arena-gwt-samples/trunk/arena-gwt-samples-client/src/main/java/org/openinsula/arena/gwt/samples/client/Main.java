package org.openinsula.arena.gwt.samples.client;

import java.util.Arrays;

import org.openinsula.arena.gwt.samples.client.vo.Role;
import org.openinsula.arena.gwt.samples.client.vo.User;
import org.openinsula.arena.gwt.serialization.client.xml.XMLSerializable;
import org.openinsula.arena.gwt.serialization.client.xml.XMLSerializer;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Main implements EntryPoint {

	public void onModuleLoad() {
		XMLSerializer serializer = GWT.create(XMLSerializable.class);
		User user = createUser();
		String xml = serializer.toXML(user);
		
		writeResult(xml);
	}
	
	private User createUser() {
		User user = new User();
		user.setId(1);
		user.setUsername("rebola");
		user.setDoubleList(Arrays.asList(1.54, 2.56));
		user.setIntegerArray(new Integer[] {1,2,3});
		user.setLongArray(new long[] {5L, 6L, 7L});
		
		Role role = new Role();
		role.setId(2);
		role.setAdmin(false);
//		role.setUserArray(new User[] {user});
		
		user.setRole(role);
		user.setRoleList(Arrays.asList(role));
		
		return user;
	}
	
	private void writeResult(final String data) {
		SimplePanel panel = new SimplePanel();
		panel.add(new Label(data));
		RootPanel.get().add(panel);
	}
	
}
