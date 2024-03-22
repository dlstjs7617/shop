package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class FileManager {
	private String fileName = "shoppingMall.txt";
	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	public FileManager(){
		file = new File(fileName);
	}
	
	private void resetSave(){
		String save = "";
		
		try {
			fw = new FileWriter(file);
			fw.write(save);
			fw.close();
			
		} catch (Exception e) {
		}
	}
	
	public void autoSave(int result,UserManager userManager, ItemManager itemManager) {
		resetSave();
		String save = "";
		save += result + ":";
		save += itemManager.saveFile() +":"; 
		save += userManager.saveFile();
		
		try {
			fw = new FileWriter(file);
			fw.write(save);
			fw.close();
			
			System.out.println("저장완료");
		} catch (Exception e) {
			System.err.println("저장실패");
		}
	}
	
	private void itemLoad(String data, ItemManager itemManager) {
		String[] dataArr = data.split("\n");
		
		for(int i=0; i<dataArr.length; i++) {
			String[] temp = dataArr[i].split(",");
			String name = temp[0];
			String brand = temp[1];
			int price = Integer.parseInt(temp[2]);
			
			itemManager.createItem(name, brand, price);
		}
	}
	private void userLoad(String data, UserManager userManager) {
		String[] dataArr = data.split("\n");
		
		for(int i=1; i<dataArr.length; i++) {
			String[] temp = dataArr[i].split(",");
			
			String id = temp[0];
			String password = temp[1];
			String name = temp[2];
			
			userManager.createUser(name, id, password);
			
			if(!temp[3].equals("null")) {
				for(int j=3; j<temp.length; j+=4) {
					String itemName = temp[j];
					String itemBrand = temp[j+1];
					int itemPrice = Integer.parseInt(temp[j+2]);
					int Amount = Integer.parseInt(temp[j+3]);
					
					Item item = new Item(itemBrand, itemName, itemPrice, Amount);
					userManager.updateUser(i, item);
				}
				
			}
		}
		
	}
	
	private int processLoad(String data, UserManager userManager, ItemManager itemManager) {
		String[] dataArr = data.split(":");
		int total = Integer.parseInt(dataArr[0]);
		Shop.loadResult(total);
		
		if(!dataArr[1].equals("null"))
		itemLoad(dataArr[1], itemManager);
		
		if(!dataArr[2].equals("null"))
		userLoad(dataArr[2], userManager);
		
		return total;
	}
	
	public int autoLoad(UserManager userManager, ItemManager itemManager) {
		String data = "";
		
		if(! file.exists()) {
			System.err.println("파일이 없습니다.");
			return 0;
		}
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			while(br.ready()) {
				data += br.readLine()+"\n";
			}
			
			
			br.close();
			fr.close();
			System.out.println("불러오기 성공");
		} catch (Exception e) {
			System.err.println("불러오기 실패");
			return 0;
		}
		
		return processLoad(data, userManager, itemManager);
		
	}
	
}
