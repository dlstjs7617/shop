package shop;

import java.util.ArrayList;

public class UserManager {
	
	private ArrayList<User> list;
	
	public UserManager() {
		list = new ArrayList<User>();
		createAdmin();
	}
	
	
	private int findIndexById(String id) {
		for(int i=0; i<list.size(); i++) {
			User temp = list.get(i);
			if(temp.getId().equals(id))
				return i;
		}
		
		return -1;
	}
	private void createAdmin() {
		String id = "admin";
		String password = "1111";
		String name = "관리자계정";
		
		createUser(name, id, password);
	}
	
	public void createUser(String name, String id, String password) {
		User user = new User(name, id, password);
		this.list.add(user);
	}
	
	public User readUser(String id) {
		int idx = findIndexById(id);
		
		if(idx == -1) {
			System.err.println("없는 유저입니다");
			return null;
		}
		User result = list.get(idx);
		return result;
	}
	
	public void updateUser() {
		//
	}
	
	
}
