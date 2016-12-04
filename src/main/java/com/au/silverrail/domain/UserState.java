package com.au.silverrail.domain;

public class UserState {
	
	private int id;
	private String state;
	
	public UserState(int id, String state) {
		super();
		this.id = id;
		this.state = state;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}	
}