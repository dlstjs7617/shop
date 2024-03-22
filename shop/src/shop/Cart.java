package shop;

import java.util.ArrayList;

public class Cart {
	
	private ArrayList<Item> list;
	
	public Cart() {
		list = new ArrayList<Item>();
	}
	
	private int findItem(Item item) {
		for(int i=0; i<list.size(); i++) {
			Item getItem = list.get(i);
			
			String name = item.getName();
			String brand = item.getBrand();
			int price = item.getPrice();
			
			if(getItem.getName().equals(name) && 
			   getItem.getBrand().equals(brand) && 
			   getItem.getPrice() == price) {
				return i;
			}
			
		}
		
		return -1;
	}
	private int findItem(String name, String brand, int price) {
		for(int i=0; i<list.size(); i++) {
			Item getItem = list.get(i);
		
			if(getItem.getName().equals(name) && 
			   getItem.getBrand().equals(brand) && 
			   getItem.getPrice() == price) {
				return i;
			}
			
		}
		
		return -1;
	}
	
	public boolean exceptionIdx(int idx) {
		if(idx < 0 || idx >= list.size()) {
			System.err.println("유효하지 않는 번호입니다.");
			return true;
		}
		
		return false;
	}
	
	
	public void printCart() {
		for(int i=0; i<list.size(); i++) {
			System.out.println(i + "번." +list.get(i));
		}
	}
	
	public int buy() {
		int total = 0;
		for(int i=0; i<list.size(); i++) {
			Item item = list.get(i);
			total += item.getPrice() * item.getAmount();
		}
		list.clear();
		return total;
	}
	
	public String saveCart() {
		String result = "";
		
		if(list.size() == 0) {
			result += "null";
			return result;
		}
		
		for(int i=0; i<list.size(); i++) {
			Item item = list.get(i);
			
			result += item.getName() +",";
			result += item.getBrand() + ",";
			result += item.getPrice() + ",";
			result += item.getAmount() + ",";
		}
		result = result.substring(0, result.length()-1);
		
		return result;
	}
	
	public void createList(Item item) {
		
		if(findItem(item) == -1) {			
			list.add(item);
			System.out.println("장바구니에 담겼습니다");
		}else if(findItem(item) != -1) {
			updateList(item);
		}
		
	}
	
	private void updateList(Item item) {
		int idx = findItem(item);
		Item temp = list.get(idx); 
		temp.setAmount(temp.getAmount() + item.getAmount());
		System.out.println("장바구니에 수량이 추가되었습니다.");
	}
	
	public void updateList(int index, int amount) {
		if(exceptionIdx(index))
			return;
		
		Item item = list.get(index);
		
		item.setAmount(amount);
		System.out.println("수량변경완료");
	}
	
	public void updateList(String name, String brand, int price, Item item) {
		int idx = findItem(name, brand, price)-1;
		
		String tempName = item.getName();
		String tempBrand = item.getBrand();
		int tempPrice = item.getPrice();
		int amount = list.get(idx).getAmount();
		
		Item temp = new Item(tempBrand, tempName, tempPrice, amount);
		list.set(idx, temp);
	}
	
	public void deleteList(int idx) {
		if(exceptionIdx(idx))
			return;
		
		list.remove(idx);
		System.out.println("삭제완료");
	}
	public void deleteList(Item item) {
		int idx = findItem(item);
		list.remove(idx);
	}
}
