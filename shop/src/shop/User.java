package shop;

import java.util.ArrayList;

public class User {
	
	private String name;
	private String id;
	private String password;
	
	private Cart cart;
	
	public User(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
		cart = new Cart();
	}
	
	public String getName() {
		return this.name;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
