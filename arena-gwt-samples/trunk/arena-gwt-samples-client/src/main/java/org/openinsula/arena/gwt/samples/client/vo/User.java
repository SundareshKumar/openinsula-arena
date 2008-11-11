package org.openinsula.arena.gwt.samples.client.vo;

import java.util.List;

import org.openinsula.arena.gwt.serialization.client.xml.XMLSerializable;

public class User implements XMLSerializable {

	private int id;

	private String username;

	private Role role;
	
	private Role[] roleArray;
	
	private List<Role> roleList;
	
	private long[] longArray;
	
	private Integer[] integerArray;
	
	private List<Double> doubleList;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(final Role role) {
		this.role = role;
	}

	public Role[] getRoleArray() {
		return roleArray;
	}

	public void setRoleArray(final Role[] roleArray) {
		this.roleArray = roleArray;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(final List<Role> roleList) {
		this.roleList = roleList;
	}

	public long[] getLongArray() {
		return longArray;
	}

	public void setLongArray(final long[] longArray) {
		this.longArray = longArray;
	}

	public Integer[] getIntegerArray() {
		return integerArray;
	}

	public void setIntegerArray(final Integer[] integerArray) {
		this.integerArray = integerArray;
	}

	public List<Double> getDoubleList() {
		return doubleList;
	}

	public void setDoubleList(final List<Double> doubleList) {
		this.doubleList = doubleList;
	}
	
	

}
