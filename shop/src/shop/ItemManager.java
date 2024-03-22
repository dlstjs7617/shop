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
	
	private boolean exceptionIndex(int idx) {
		if(idx < 0 || idx >= list.size()) {
			System.err.println("유효하지않은 인덱스입니다.");
			return true;
		}
		
		return false;
	}
	
	public void printAllItem() {
		for(int i=0; i<list.size(); i++) {
			System.out.println(i + "번" + list.get(i));
		}
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
	
	public void deleteItem(int idx) {
		if(exceptionIndex(idx)) {
			return;
		}
		
		list.remove(idx);
		System.out.println("아이템 삭제 완료");
	}
	
	
	
}
