package LiqueurDepartment.controller;

import LiqueurDepartment.view.ConsoleView;
import LiqueurDepartment.model.BookStorage;
import LiqueurDepartment.model.Cart;

public class LiqueurDepartmentController {

	ConsoleView view;
	LiqueurStorage mLiqueurStorage;
	Cart mCart
	Customer mCustomer;

	String[] menuList = { "0. 종료", "1. 주류 정보 보기", "2. 장바구니 보기", "3. 장바구니에 주류 추가", "4. 장바구니 주류 삭제", "5. 장바구니 주류 수량 변경",
			"6. 장바구니 비우기", "7. 이름 또는 종류로 주류 검색", "8. 주문" };

	public LiqueurDepartmentController(LiqueurStorage liqueurStorage, Cart cart, ConsoleView view) {
		this.view = view;
		this.mLiqueurStorage = LiqueurStorage;
		this.mCart = cart;
	}

	public void Start() {
		welcome();
		registerCustomerInfo();

		int menu;

		do {
			menu = view.selectMenu(menuList);

			switch (menu) {
			case 1 -> viewLiqueurInfo();
			case 2 -> viewCart();
			case 3 -> addLiqueur2Cart();
			case 4 -> deleteLiqueurInCart();
			case 5 -> updateLiqueurInCart();
			case 6 -> resetCart();
			case 7 -> search();
			case 8 -> order();
			case 0 -> end();
			default -> view.showMessage("잘못된 메뉴 번호입니다.");
			}
		} while (menu != 0);
	}

	// 환영 인사
	private void welcome() {
		view.displayWelcome();
	}

	// 고객 정보 등록
	private void registerCustomerInfo() {
		mCustomer = new Customer();
		view.inputCustomerInfo(mCustomer);
	}

	// 주류 정보 보기
	private void viewLiqueurInfo() {
		view.displayBookInfo(mLiqueurStorage);
	}

	// 장바구니 보기
	private void viewCart() {
		view.displayCart(mCart);
	}

	// 장바구니에 주류 추가
	private void addLiqueur2Cart() {
		view.displayBookInfo(mLiqueurStorage);
		int bookId = view.selectBookId(mLiqueurStorage);
		mCart.addItem(mLiqueurStorage.getLiqueurId(LiqueurId));
		view.showMessage(">>장바구니에 주류를 추가하였습니다.");
	}

	// 장바구니 주류 삭제
	private void deleteLiqueurInCart() {
		// 장바구니 보여주기
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			// 도서 ID 입력 받기
			int LiqueurId = view.selectLiqueurId(mCart);
			if (view.askConfirm(">> 해당 주류를 삭제하려면 yes를 입력하세요 : ", "yes")) {
				// 해당 도서 ID의 cartItem 삭제
				mCart.deleteItem(LiqueurId);
				view.showMessage(">> 해당 주류를 삭제했습니다.");
			}
		}
	}

	// 장바구니 도서 수량 변경
	private void updateBookInCart() {
		// 장바구니 보여주기
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			// 도서 ID 입력 받기
			int bookId = view.selectLiqueurId(mCart);
			// 수량 입력 받기
			int quantity = view.inputQuantity(0, mLiqueurStorage.getMaxQuantity());
			// 주류 ID에 해당하는 cartItem 가져와서 cartItem quantity set 수량
			mCart.updateQuantity(LiqueurId, quantity);
		}
	}

	// 장바구니 비우기
	private void resetCart() {
		view.displayCart(mCart);

		if (!mCart.isEmpty()) {
			if (view.askConfirm(">> 장바구니를 비우려면 yes를 입력하세요 : ", "yes")) {
				mCart.resetCart();
				view.showMessage(">> 장바구니를 비웠습니다.");
			}
		}

	}
	// 검색 기능 추가
	private void search() {
		view.showMessage(">> 검색어를 입력해주세요.");
		
	}

	// 주문 정보 추가
	private void order() {
		// 배송 정보 추가
		getDeliveryInfo();
		// 구매 정보 보기 : 장바구니 내역, 배송 정보
		viewOrderInfo();
		// 진짜 주문할거니?
		if (view.askConfirm("진짜 주문하려면 yes를 입력하세요 : ", "yes")) {
			// 주문 처리 -> 장바구니 초기화
			mCart.resetCart();
		}
	}

	private void getDeliveryInfo() {
		view.inputDeliveryInfo(mCustomer);
	}

	private void viewOrderInfo() {
		view.displayOrder(mCart, mCustomer);
	}

// 종료
	private void end() {
		view.showMessage(">> LiqueurDepartment을 종료합니다.");
	}
}