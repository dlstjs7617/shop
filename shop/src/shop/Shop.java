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
	private final int TOTAL = 5;
	
	private final int MY_CART = 1;
	private final int DELETE_CART_ITEM = 2;
	private final int SET_AMOUNT = 3;
	private final int BUY = 4;
	
	
	private Scanner sc = new Scanner(System.in);
	
	private UserManager userManager;
	private ItemManager itemManager;
	private FileManager fileManager;
	private String brand;
	private int log;
	private static int result;

	public Shop(String brand) {
		this.brand = brand;
		userManager = new UserManager();
		itemManager = new ItemManager();
		fileManager = new FileManager();
		log = -1;
		result = 0;
	}
	
	public static void loadResult(int total) {
		result = total;
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
	
	private void addItem() {
		String name = inputString("제품명");
		String brand = inputString("제조사");
		int price = inputNumber("가격");
		
		itemManager.createItem(name, brand, price);
	}
	
	private void deleteItem() {
		itemManager.printAllItem();
		int index = inputNumber("삭제할 상품 인덱스");
		
		itemManager.deleteItem(index);

	}
	
	private void updateItem() {
		itemManager.printAllItem();
		int index = inputNumber("수정할 상품 인덱스");
		
		String name = inputString("상품명");
		String brand = inputString("제조사");
		int price = inputNumber("가격");
		
		if(price <= 0) {
			System.err.println("유효하지 않은 가격입니다.");
			return;
		}
		
		Item item = new  Item(brand, name, price);
		
		itemManager.updateItem(index, item);
		// 유저 장바구니변경도 추가예정
		userManager.updateAllItem(name, brand, price, item);
		
		
	}
	
	private void logout() {
		log = IS_LOGGED_IN;
		System.out.println("로그아웃 하셨습니다.");
	}
	
	private void total() {
		System.out.printf("현재 총매출 %d원 \n",result);
	}
	
	private void shopping() {
		itemManager.printAllItem();
		
		int idx = inputNumber("구매하실 아이템 선택");
		int amount = inputNumber("구매할 수량 입력");
		
		if(itemManager.readItem(idx, amount) == null) {
			return;
		}
				
		Item item = itemManager.readItem(idx, amount);
		userManager.updateUser(log, item);
		
	}
	
	private void printMyCart() {
		userManager.printMyCart(log);
	}
	
	private void deleteMyItem() {
		printMyCart();
		
		int idx = inputNumber("삭제할 상품 인덱스 입력");
		
		userManager.deleteMyCartItem(log, idx);
	}
	
	private void setAmount() {
		printMyCart();
		
		int idx = inputNumber("수량변경할 상품 인덱스 입력");
		int amount = inputNumber("변경할 수량 입력");
		
		if(amount <=0) {
			System.err.println("유효하지않는 수량");
			return;
		}
		
		userManager.setAmount(log, idx, amount);
	}
	
	private void buy() {
		userManager.printMyCart(log);
		int money = userManager.buy(log);
		result += money;
		System.out.printf("%d원 결제되셨습니다. \n", money);
	}
	
	private void printMyPage() {
		System.out.println("1.내 장바구니");
		System.out.println("2.항목삭제");
		System.out.println("3.수량수정");
		System.out.println("4.결제");
	}
	
	private void runMyPage() {
		int sel = inputNumber("메뉴 선택");
		
		if(sel == MY_CART) 
			printMyCart();
		else if(sel == DELETE_CART_ITEM)
			deleteMyItem();
		else if(sel == SET_AMOUNT)
			setAmount();
		else if(sel == BUY)
			buy();
	}
	
	private void myPage() {
		printMyPage();
		runMyPage();
	}
	
	private void leave() {
		userManager.deleteUser(log);
		log = IS_LOGGED_IN;
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
			if(select == ADD_ITEM)
				addItem();
			else if(select == DELETE_ITEM)
				deleteItem();
			else if(select == UPDATE_ITEM)
				updateItem();
			else if(select == LOG_OUT)
				logout();
			else if(select == TOTAL)
				total();
			
		}else if(log != IS_LOGGED_IN) {
			if(select == LEAVE) 
				leave();
			else if(select == SHOPPING) 
				shopping();
			else if(select == MY_PAGE)
				myPage();
			else if(select == LOG_OUT)
				logout();
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
			System.out.println("5.현재 매출 확인");			
		}else if(log != IS_LOGGED_IN) {
			System.out.println("==============");
			System.out.println(brand + "쇼핑몰");
			System.out.println("==============");
			System.out.println("1.탈퇴");
			System.out.println("2.쇼핑하기");
			System.out.println("3.마이페이지");
			System.out.println("4.로그아웃");
		}
	}
	
	private void autoSave() {
		fileManager.autoSave(result,userManager, itemManager);
	}
	
	private void autoLoad() {
		fileManager.autoLoad();
	}
	
	private boolean isRun() {
		return true;
	}
	private void shopRun() {
		while(isRun()) {
			autoLoad();
			printMenu();
			selectMenu();
			autoSave();
		}
	}
	
	public void run() {
		shopRun();
	}
}
