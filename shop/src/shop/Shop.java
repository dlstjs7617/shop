package shop;

import java.util.Scanner;

public class Shop {
	
	private final int IS_LOGGED_IN = -1;
	private final int IS_LOGIN_ADMIN = 0;
	
	private Scanner sc = new Scanner(System.in);
	
	private UserManager userManager;
	private ItemManager itemManager;

	private String brand;
	private int log;
	
	private int inputNumber(String message) {
		int number = -1;
		System.out.print(message + " : ");
		
		try {
			String input = sc.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자만 입력하세요.");
		}
		
		return number;
	}
	
	private String inputString(String message) {
		System.out.print(message + " : ");
		return sc.next();
	}
	
	public Shop(String brand) {
		this.brand = brand;
		userManager = new UserManager();
		itemManager = new ItemManager(); 
	}
	
	private void printMenu() {
		if(log == IS_LOGGED_IN) {
			System.out.println("1.회원가입");
			System.out.println("2.로그인");			
		}else if(log != IS_LOGGED_IN) {
			System.out.println("1.탈퇴");
			System.out.println("2.쇼핑하기");
			System.out.println("3.마이페이지");
		}else if(log == IS_LOGIN_ADMIN) {
			System.out.println("1.아이템등록");
			System.out.println("2.아이템삭제");
			System.out.println("3.아이템수정");
		}
	}
	
	private boolean isRun() {
		return true;
	}
	private void shopRun() {
		while(isRun()) {
			printMenu();
			
		}
	}
	
	public void run() {
		shopRun();
	}
}
