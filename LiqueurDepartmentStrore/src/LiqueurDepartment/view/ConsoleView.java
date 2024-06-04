package LiqueurDepartment.view;

import java.util.Scanner;

import LiqueurDepartment.model.Cart;
import LiqueurDepartment.model.Customer;
import LiqueurDepartment.model.LiqueurStorage;

public class ConsoleView {

	public void displayWelcome() {
		String welcome = "******************************************\n" + "*  	   주류 백화점에 오신 것을 환영합니다. 	   *\n"
				+ "******************************************";
		System.out.println(welcome);
	}

	public int selectMenu(String[] menuList) {

		displayMenu(menuList);

		int menu;
		do {
			System.out.println(">> 메뉴 선택 : ");
			menu = readNumber();
			if (menu < 0 || menu >= menuList.length)
				System.out.println("0부터 " + (menuList.length - 1) + "까지의 숫자를 입력하세요.");
		} while (menu < 0 || menu >= menuList.length);
		return menu;
	}

	// 메뉴 출력
	private void displayMenu(String[] menuList) {
		System.out.println("=========================================");
		for (int i = 1; i < menuList.length; i++) {
			System.out.println(menuList[i]);
		}
		System.out.println(menuList[0]);
		System.out.println("=========================================");
	}

	// 주류 목록 뷰
	public void displayBookInfo(LiqueurStorage LiqueurStorage) {
		for (int i = 0; i < LiqueurStorage.getNumLiqueur(); i++) {
			String bookInfo = LiqueurStorage.getLiqueurInfo(i);
			System.out.println(bookInfo);

		}
	}

	// 장바구니 뷰
	public void displayCart(Cart cart) {
		if (cart.isEmpty()) {
			System.out.println(">> 장바구니가 비어 있습니다.");
			return;
		}

		for (int i = 0; i < cart.getNumItems(); i++) {
			System.out.println(cart.getItemInfo(i));
		}
		System.out.println("총 금액 : " + cart.getTotalPrice() + "원");
	}

	public int selectLiqueur(Cart cart) {

		int LiqueurId;
		boolean result;
		do {
			System.out.print("도서 ID 입력 : ");
			LiqueurId = readNumber();
			result = cart.isValidItem(LiqueurId);
			if (!result)
				System.out.print("없는 주류 입니다.");
		} while (!result);

		return LiqueurId;
	}

	// 주류 수량 입력 받기
	public int inputQuantitiy(int min, int max) {
		int number;
		do {
			System.out.print(">> 수량 입력 (" + min + " ~ " + max + "): ");
			number = readNumber();
			if (number < min || number > max)
				System.out.println("잘못된 수량입니다.");
		} while (number < min || number > max);

		return number;
	}

	// 고객 정보 입력 받기
	public void inputCustomerInfo(Customer customer) {
		Scanner input = new Scanner(System.in);
		System.out.println("구매하시려면 이름과 전화번호를 입력하세요.");
		System.out.print(">> 이름 : ");
		customer.setName(input.nextLine());
		System.out.print(">> 전화번호 : ");
		customer.setPhone(input.nextLine());
	}

	// 배송 정보 입력 받기 - 주소와 이메일주소가 없는 경우
	public void inputDeliveryInfo(Customer customer) {
		if (customer.getEmail() == null) {
			Scanner input = new Scanner(System.in);
			System.out.println("배송을 위하여 이메일 주소와 배송받을 곳의 주소를 입력받습니다.");
			System.out.print(">> 이메일 : ");
			customer.setEmail(input.nextLine());
			System.out.print(">> 주소 : ");
			customer.setAddress(input.nextLine());
		}
	}

	public void displayOrder(Cart cart, Customer customer) {

		System.out.println();
		// 장바구니 보여주기
		System.out.println("***** 주문할 도서 ******");
		displayCart(cart);

		// 배송 정보 보여주기 - 고객 이름, 전화번호, 주소, 이메일주소
		System.out.println("***** 배송 정보 ******");
		System.out.println(">> 이름 : " + customer.getName());
		System.out.println(">> 전화번호 : " + customer.getPhone());
		System.out.println(">> 이메일 : " + customer.getEmail());
		System.out.println(">> 주소 : " + customer.getAddress());
		System.out.println();

	}

	///////////////////// 공용 모듈 ////////////////////////

	// 입력된 문자열을 입력했을 때만 true를 반환하는 confirm용
	public boolean askConfirm(String message, String yes) {

		System.out.print(message);

		Scanner input = new Scanner(System.in);
		if (input.nextLine().equals(yes))
			return true;
		return false;

	}

	// 숫자 입력 받기 (숫자가 아닌 문자를 넣으면 예외 처리하고 다시 입력받기)
	private int readNumber() {
		Scanner input = new Scanner(System.in);
		try {
			int number = input.nextInt();
			return number;
		} catch (Exception e) {
			System.out.print("숫자를 입력하세요 :");
			return readNumber();
		}
	}

	// 입력된 문자열 출력
	public void showMessage(String message) {
		System.out.println(message);

	}

}
