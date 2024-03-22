package shop;

import java.util.ArrayList;

public class UserManager {
	
	private ArrayList<User> list;
	
	public UserManager() {
		list = new ArrayList<User>();
		createAdmin();
	}
	
	
	public int findIndexById(String id) {
		for(int i=0; i<list.size(); i++) {
			User user = list.get(i);
			if(user.getId().equals(id))
				return i;
		}
		
		return -1;
	}
	
	public int findUserLogin(String id, String password) {
		for(int i=0; i<list.size(); i++) {
			User user = list.get(i);
			
			if(user.getId().equals(id) && user.getPassword().equals(password))
				return i;
		}
		
		return -1;
	}
	
	public void printMyCart(int log) {
		User user = list.get(log);
		user.getCart().printCart();
	}
	
	public void deleteMyCartItem(int log, int index) {
		User user = list.get(log);
		user.getCart().deleteList(index);
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
	
	public void updateUser(int log,Item item) {
		User user = list.get(log);
		Cart cart = user.getCart();
		
		cart.createList(item);
	}
	
	public void deleteUser(int log) {
		list.remove(log);
	}
	
}
