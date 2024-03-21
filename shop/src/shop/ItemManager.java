package shop;

import java.util.ArrayList;

public class ItemManager {

	private ArrayList<Item> list;
	
	public ItemManager() {
		list = new ArrayList<Item>();
	}
	
	private boolean checkItem(String name, String brand, String price) {
		for(int i=0; i<list.size(); i++) {
			Item item = list.get(i);
			
			if(item.getName().equals(name) && item.getBrand().equals(brand))
				return false;
		}
		
		return true;
	}
	
	
}
