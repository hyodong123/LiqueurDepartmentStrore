package LiqueurDepartment.view;

import java.util.ArrayList;
import java.util.Scanner;

import LiqueurDepartment.model.Cart;
import LiqueurDepartment.model.Customer;
import LiqueurDepartment.model.Liqueur;
import LiqueurDepartment.model.LiqueurStorage;
import LiqueurDepartment.model.Order;

public class ConsoleView {

	// 환영 인사 출력
	public void displayWelcome() {
		String welcome = "******************************************\n" + "*       Welcome to Liqueur Market        *\n"
				+ "******************************************";
		System.out.println(welcome);
	}

	// 메뉴 번호 입력 받기
	public int selectMenu(String[] menuList) {

		displayMenu(menuList);

		int menu;
		do {
			System.out.print(">> 메뉴 선택 : ");
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

	// 주류 목록 보여주기
	public void displayLiqueurInfo(LiqueurStorage liqueurStorage) {
		for (int i = 0; i < liqueurStorage.getNumLiqueurs(); i++) {
			String liqueurInfo = liqueurStorage.getLiqueurInfo(i);
			System.out.println(liqueurInfo);
		}
	}

	// 장바구니 보여주기
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

	// liqueurStorage에 있는 주류를 ID로 선택하기
	public int selectLiqueurId(LiqueurStorage liqueurStorage) {

		int liqueurId;
		boolean result;
		do {
			System.out.print("추가할 주류의 ID를 입력하세요 : ");
			liqueurId = readNumber();
			result = liqueurStorage.isValidLiqueur(liqueurId);
			if (!result)
				System.out.print("잘못된 주류의 ID입니다.");
		} while (!result);

		return liqueurId;
	}

	// cart에 있는 주류를 ID로 선택하기
	public int selectLiqueurId(Cart cart) {

		int liqueurId;
		boolean result;
		do {
			System.out.print("주류 ID 입력 : ");
			liqueurId = readNumber();
			result = cart.isValidItem(liqueurId);
			if (!result)
				System.out.print("잘못된 주류의 ID입니다.");
		} while (!result);

		return liqueurId;
	}

	// 주류 수량 입력 받기
	public int inputQuantity(int min, int max) {
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
		System.out.println("온라인 주류 백화점을 이용하시려면 이름과 전화번호를 입력하세요.");
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
		System.out.println("***** 주문할 주류 ******");
		displayCart(cart);

		// 배송 정보 보여주기 - 고객 이름, 전화번호, 주소, 이메일주소
		System.out.println("***** 배송 정보 ******");
		System.out.println(">> 이름 : " + customer.getName());
		System.out.println(">> 전화번호 : " + customer.getPhone());
		System.out.println(">> 이메일 : " + customer.getEmail());
		System.out.println(">> 주소 : " + customer.getAddress());
		System.out.println();

	}

	// 이름 또는 종류로 검색
	public String inputSearchKeyword() {
		Scanner input = new Scanner(System.in);
		System.out.print("검색할 주류 이름 또는 종류(3글자 이상 입력하세요) : ");
		return input.nextLine();
	}

	public void displaySearchResults(ArrayList<Liqueur> results) {
		if (results.isEmpty()) {
			System.out.println("검색 결과가 없습니다.");
		} else {
			for (Liqueur liqueur : results) {
				System.out.println(liqueur.toString());
			}
		}
	}

	// 카테고리로 검색
	public String inputSearchCategory() {
		Scanner input = new Scanner(System.in);
		System.out.print("검색할 주류 카테고리 : ");
		return input.nextLine();
	}

	public void displayCategoryResults(ArrayList<Liqueur> results) {
		if (results.isEmpty()) {
			System.out.println("해당 카테고리에 대한 검색 결과가 없습니다.");
		} else {
			for (Liqueur liqueur : results) {
				System.out.println(liqueur.toString());
			}
		}
	}

	// 관리자 로그인 입력
	public String inputAdminId() {
		Scanner input = new Scanner(System.in);
		System.out.print("관리자 ID를 입력하세요: ");
		return input.nextLine();
	}

	public String inputAdminPassword() {
		Scanner input = new Scanner(System.in);
		System.out.print("관리자 비밀번호를 입력하세요: ");
		return input.nextLine();
	}

	// 주류 상세 정보 입력 받기
	public Liqueur inputLiqueurDetails() {
		Scanner input = new Scanner(System.in);

		int id = 0;
		boolean validInput = false;

		while (!validInput) {
			try {
				System.out.print("주류 ID: ");
				id = Integer.parseInt(input.nextLine());
				validInput = true;
			} catch (NumberFormatException e) {
				System.out.println("숫자를 입력하세요.");
			}
		}

		System.out.print("주류 이름: ");
		String name = input.nextLine();

		System.out.print("주류 종류-카테고리: ");
		String typeCategory = input.nextLine();

		System.out.print("알코올 도수: ");
		double alcoholContent = Double.parseDouble(input.nextLine());

		System.out.print("원산지: ");
		String origin = input.nextLine();

		System.out.print("용량 (ml): ");
		int volume = Integer.parseInt(input.nextLine());

		System.out.print("가격 (원): ");
		int price = Integer.parseInt(input.nextLine());

		return new Liqueur(id, name, typeCategory, alcoholContent, origin, volume, price);
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

	public void displayAllOrders(ArrayList<Order> orders) {
		System.out.println("***** 모든 주문 보기 ******");
		for (Order order : orders) {
			System.out.println(order.toString());
		}
	}
}