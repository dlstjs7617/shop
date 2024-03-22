package shop;

public class Item {
	
	private String name;
	private String brand;
	private int price;
	private int amount;
	
	public Item(String brand, String name, int price) {
		this.name = name;
		this.brand = brand;
		this.price = price;
	}
	
	public Item(String brand, String name, int price, int amount) {
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.amount = amount;
	}


	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public int getPrice() {
		return this.price;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s) : %d", name,brand,price);
	}
	
}
