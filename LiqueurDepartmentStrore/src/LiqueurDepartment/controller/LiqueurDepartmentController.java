package LiqueurDepartment.controller;

import java.util.ArrayList;

import LiqueurDepartment.model.Admin;
import LiqueurDepartment.model.Cart;
import LiqueurDepartment.model.Customer;
import LiqueurDepartment.model.Liqueur;
import LiqueurDepartment.model.LiqueurStorage;
import LiqueurDepartment.model.Order;
import LiqueurDepartment.model.OrderNumberManager;
import LiqueurDepartment.model.OrderReader;
import LiqueurDepartment.model.OrderWriter;
import LiqueurDepartment.view.ConsoleView;

public class LiqueurDepartmentController {
	private ConsoleView view;
	private LiqueurStorage mLiqueurStorage;
	private Cart mCart;
	private Customer mCustomer;
	private boolean isAdminMode = false;

	String[] mainMenuList = { "0. 종료", "1. 로그인", "2. 주류 정보 보기", "3. 장바구니 보기", "4. 장바구니에 주류 추가", "5. 장바구니 주류 삭제",
			"6. 장바구니 주류 수량 변경", "7. 장바구니 비우기", "8. 이름 또는 종류로 주류 검색", "9. 주문" };

	String[] adminMenuList = { "0. 종료", "1. 주류 정보 보기", "2. 주류 추가", "3. 주류 삭제", "4. 주류 수정", "5. 모든 주문 보기", "6. 로그아웃" };

	public LiqueurDepartmentController(LiqueurStorage liqueurStorage, Cart cart, ConsoleView view) {
		this.view = view;
		this.mLiqueurStorage = liqueurStorage;
		this.mCart = cart;
	}

	public void start() {
		welcome();

		int menu;

		do {
			menu = view.selectMenu(isAdminMode ? adminMenuList : mainMenuList);

			if (isAdminMode) {
				switch (menu) {
				case 1 -> viewLiqueurInfo();
				case 2 -> addLiqueur();
				case 3 -> deleteLiqueur();
				case 4 -> updateLiqueur();
				case 5 -> viewAllOrders();
				case 6 -> logout();
				case 0 -> end();
				default -> view.showMessage("잘못된 메뉴 번호입니다.");
				}
			} else {
				switch (menu) {
				case 1 -> login();
				case 2 -> viewLiqueurInfo();
				case 3 -> viewCart();
				case 4 -> addLiqueur2Cart();
				case 5 -> deleteLiqueurInCart();
				case 6 -> updateLiqueurInCart();
				case 7 -> resetCart();
				case 8 -> searchByNameOrType();
				case 9 -> order();
				case 0 -> end();
				default -> view.showMessage("잘못된 메뉴 번호입니다.");
				}
			}
		} while (menu != 0);
	}

	private void login() {
		String id = view.inputAdminId();
		String password = view.inputAdminPassword();
		if (Admin.isAdmin(id, password)) {
			isAdminMode = true;
			view.showMessage(">> 관리자 모드로 로그인되었습니다.");
		} else {
			view.showMessage(">> 로그인 실패: 잘못된 관리자 ID 또는 비밀번호입니다.");
		}
	}

	private void logout() {
		isAdminMode = false;
		view.showMessage(">> 로그아웃되었습니다.");
	}

	private void welcome() {
		view.displayWelcome();
	}

	private void registerCustomerInfo() {
		mCustomer = new Customer();
		view.inputCustomerInfo(mCustomer);
	}

	private void viewLiqueurInfo() {
		view.displayLiqueurInfo(mLiqueurStorage);
	}

	private void viewCart() {
		view.displayCart(mCart);
	}

	private void addLiqueur2Cart() {
		view.displayLiqueurInfo(mLiqueurStorage);
		int liqueurId = view.selectLiqueurId(mLiqueurStorage);
		mCart.addItem(mLiqueurStorage.getLiqueurById(liqueurId));
		view.showMessage(">> 장바구니에 주류를 추가하였습니다.");
	}

	private void deleteLiqueurInCart() {
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			int liqueurId = view.selectLiqueurId(mCart);
			if (view.askConfirm(">> 해당 주류를 삭제하려면 yes를 입력하세요 : ", "yes")) {
				mCart.deleteItem(liqueurId);
				view.showMessage(">> 해당 주류를 삭제했습니다.");
			}
		}
	}

	private void updateLiqueurInCart() {
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			int liqueurId = view.selectLiqueurId(mCart);
			int quantity = view.inputQuantity(0, mLiqueurStorage.getMaxQuantity());
			mCart.updateQuantity(liqueurId, quantity);
		}
	}

	private void resetCart() {
		view.displayCart(mCart);

		if (!mCart.isEmpty()) {
			if (view.askConfirm(">> 장바구니를 비우려면 yes를 입력하세요 : ", "yes")) {
				mCart.resetCart();
				view.showMessage(">> 장바구니를 비웠습니다.");
			}
		}
	}

	private void searchByNameOrType() {
		String keyword = view.inputSearchKeyword();
		if (keyword.length() >= 3) {
			var results = mLiqueurStorage.searchLiqueurByNameOrType(keyword);
			view.displaySearchResults(results);
		} else {
			view.showMessage("검색어는 3글자 이상이어야 합니다.");
		}
	}

//    private void searchByCategory() {
//        String category = view.inputSearchCategory();
//        var results = mLiqueurStorage.searchLiqueurByCategory(category);
//        view.displayCategoryResults(results);
//    }

	private void order() {
		if (mCart.isEmpty()) {
			view.showMessage(">> 장바구니가 비어 있습니다. 주문을 진행할 수 없습니다.");
			return;
		}

		if (mCustomer == null) {
			registerCustomerInfo();
		}

		getDeliveryInfo();
		viewOrderInfo();
		if (view.askConfirm("진짜 주문하려면 yes를 입력하세요 : ", "yes")) {
			mCart.resetCart();
			view.showMessage(">> 주문이 완료되었습니다.");

			int orderId = OrderNumberManager.getNextOrderNumber(); // 주문 번호 가져오기
			OrderWriter.writeOrder(new Order(orderId, mCustomer, mCart)); // 주문 정보 파일에 저장
		}
	}

	private void getDeliveryInfo() {
		view.inputDeliveryInfo(mCustomer);
	}

	private void viewOrderInfo() {
		view.displayOrder(mCart, mCustomer);
	}

	private void end() {
		view.showMessage(">> LiqueurDepartment을 종료합니다.");
	}

	// 관리자 모드 기능
	private void addLiqueur() {
		Liqueur liqueur = view.inputLiqueurDetails();
		mLiqueurStorage.addLiqueur(liqueur);
		view.showMessage(">> 주류가 추가되었습니다.");
	}

	private void deleteLiqueur() {
		view.displayLiqueurInfo(mLiqueurStorage);
		int liqueurId = view.selectLiqueurId(mLiqueurStorage);
		if (mLiqueurStorage.deleteLiqueur(liqueurId)) {
			view.showMessage(">> 주류가 삭제되었습니다.");
		} else {
			view.showMessage(">> 주류 삭제에 실패하였습니다.");
		}
	}

	private void updateLiqueur() {
		view.displayLiqueurInfo(mLiqueurStorage);
		int liqueurId = view.selectLiqueurId(mLiqueurStorage);
		Liqueur updatedLiqueur = view.inputLiqueurDetails();
		if (mLiqueurStorage.updateLiqueur(liqueurId, updatedLiqueur)) {
			view.showMessage(">> 주류 정보가 수정되었습니다.");
		} else {
			view.showMessage(">> 주류 정보 수정에 실패하였습니다.");
		}
	}

	private void viewAllOrders() {
		ArrayList<Order> orders = OrderReader.readOrders();
		if (orders.isEmpty()) {
			view.showMessage("주문 내역이 없습니다.");
		} else {
			for (Order order : orders) {
				view.displayOrder(order.getCart(), order.getCustomer());
			}
		}
	}
}