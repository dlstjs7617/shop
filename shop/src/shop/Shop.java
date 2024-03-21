package shop;

import java.util.Scanner;

public class Shop {
	
	private final int IS_LOGGED_IN = -1;
	private final int IS_LOGIN_ADMIN = 0;

	private final int SIGN_UP = 1;
	private final int LOG_IN = 2;

	private final int LEAVE = 1;
	private final int SHOPPING = 2;
	private final int MY_PAGE = 3;
	private final int LOG_OUT = 4;

	private final int ADD_ITEM = 1;
	private final int DELETE_ITEM = 2;
	private final int UPDATE_ITEM = 3;
	
	private Scanner sc = new Scanner(System.in);
	
	private UserManager userManager;
	private ItemManager itemManager;

	private String brand;
	private int log;

	public Shop(String brand) {
		this.brand = brand;
		userManager = new UserManager();
		itemManager = new ItemManager();
		log = -1;
	}
	
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
	
	private void signUp() {
		String id = inputString("아이디");
		if(userManager.findIndexById(id) != -1) {
			System.err.println("이미 존재하는 아이디입니다.");
			return;
		}
		
		String password = inputString("비밀번호");
		String name = inputString("이름");
		
		userManager.createUser(name, id, password);
		System.out.println("회원가입 완료");
	}
	
	private void login() {
		String id = inputString("아이디");
		String password = inputString("비밀번호");
		
		if(userManager.findUserLogin(id, password) == -1) {
			System.err.println("아이디/비밀번호가 틀렸습니다.");
			return;
		}
		
		log = userManager.findIndexById(id);
		
	}
	
	private void leave() {
		log = -1;
		
		System.out.println("회원 탈퇴 완료");
	}
	
	private void selectMenu() {
		int select = inputNumber("메뉴");
		
		if(log == IS_LOGGED_IN) {
			if(select == SIGN_UP)
				signUp();
			else if(select == LOG_IN)
				login();
		}else if(log == IS_LOGIN_ADMIN) {
//			if(select == ADD_ITEM)
//				addItem();
//			else if(select == DELETE_ITEM)
//				deleteItem();
//			else if(select == UPDATE_ITEM)
//				updateITEM();
//			else if(select == LOG_OUT)
//				logout();
//			
		}else if(log != IS_LOGGED_IN) {
			if(select == LEAVE) 
				leave();
//			else if(select == SHOPPING) 
//				shopping();
//			else if(select == MY_PAGE)
//				myPage();
//			else if(select == LOG_OUT)
//				logout();
		}
	}
	
	private void printMenu() {
		if(log == IS_LOGGED_IN) {
			System.out.println("1.회원가입");
			System.out.println("2.로그인");
		}else if(log == IS_LOGIN_ADMIN) {
			System.out.println("1.아이템등록");
			System.out.println("2.아이템삭제");
			System.out.println("3.아이템수정");
			System.out.println("4.로그아웃");
		}else if(log != IS_LOGGED_IN) {
			System.out.println("1.탈퇴");
			System.out.println("2.쇼핑하기");
			System.out.println("3.마이페이지");
			System.out.println("4.로그아웃");
		}
	}
	
	private boolean isRun() {
		return true;
	}
	private void shopRun() {
		while(isRun()) {
			printMenu();
			selectMenu();
		}
	}
	
	public void run() {
		shopRun();
	}
}
