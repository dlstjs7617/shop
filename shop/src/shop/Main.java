package shop;

public class Main {

	public static void main(String[] args) {
		Shop shop = new Shop("Mega");
		shop.run();
		
		// Shop Project
		// class : Item, ItemManager, User, UserManager, Cart, FileManager, Shop
		
		// method :
		
		// 유저 -
		// ㄴ 회원가입/탈퇴/
		// ㄴ 로그인/로그아웃/
		// ㄴ 쇼핑하기/
		// ㄴ 마이페이지(내장바구니,항목,삭제,수량수정,결제)
		// ㄴ 자동저장/자동로드
		
		// 관리자
		// ㄴ 아이템등록/삭제/수정/
		// ㄴ 조회(총 매출)
		// * 커밋은 완성된 기능 단위로 할 것! 
				// ㄴ 디버깅 필 
				
				// 유저 - 
				// ㄴ 회원가입					[O]
				// ㄴ 탈퇴					[O]
				// ㄴ 로그인					[O]
				// ㄴ 로그아웃					[O]
				// ㄴ 쇼핑하기					[X]
				// ㄴ 마이페이지
				//   ㄴ 내장바구니				[X]
				//   ㄴ 항목삭제				[X]
				//   ㄴ 수량수정				[X]
				//   ㄴ 결제					[X]
				// 파일 
				// ㄴ 자동저장					[X]
				// ㄴ 자동로드					[X]
				
				// 관리자 -
				// ㄴ 아이템		
				//   ㄴ 등록					[O]
				//   ㄴ 삭제					[O]
				//   ㄴ 수정					[X]
				// ㄴ 조회(총 매출)			[X]
	}

}
