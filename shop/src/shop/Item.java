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

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public int getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s) : %d", name,brand,price);
	}
	
}
