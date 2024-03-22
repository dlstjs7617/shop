package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private String fileName = "shoppingMall.txt";
	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	public FileManager(){
		file = new File(fileName);
	}
	
	public void autoSave(UserManager userManager, ItemManager itemManager) {
		String save = "";
		save += userManager.saveFile() +"^";
		save += itemManager.saveFile() +"^";
		
		try {
			fw = new FileWriter(file);
			fw.write(save);
			fw.close();
			
			System.out.println("저장완료");
		} catch (Exception e) {
			System.err.println("저장실패");
		}
	}
	
}
