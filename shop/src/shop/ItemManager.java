package shop;

import java.util.ArrayList;

public class ItemManager {

	private ArrayList<Item> list;
	
	public ItemManager() {
		list = new ArrayList<Item>();
	}
	
	private boolean checkItem(String name, String brand) {
		for(int i=0; i<list.size(); i++) {
			Item item = list.get(i);
			
			if(item.getName().equals(name) && item.getBrand().equals(brand))
				return false;
		}
		
		return true;
	}

	public void createItem(String name, String brand, int price) {
		if(checkItem(name, brand)) {
			Item item = new Item(brand, name, price);
			list.add(item);
			
			System.out.println("상품 추가 완료");
		}else {
			System.err.println("이미 있는 상품입니다");
		}
	}
	
	
}
