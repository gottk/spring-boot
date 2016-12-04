package com.au.silverrail.domain;

public class UserCharacters {
	
	private int id;
	private String characters;
	
	public UserCharacters(int id, String characters) {
		super();
		this.id = id;
		this.characters = characters;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCharacters() {
		return characters;
	}
	public void setCharacters(String characters) {
		this.characters = characters;
	}
}
