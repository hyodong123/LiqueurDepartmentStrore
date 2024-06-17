package liqueurDepartment.view;

import java.util.ArrayList;
import java.util.Scanner;

import liqueurDepartment.model.Cart;
import liqueurDepartment.model.Liqueur;
import liqueurDepartment.model.LiqueurStorage;
import liqueurDepartment.user.customer.Customer;

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

	public int selectLiqueurId(LiqueurStorage liqueurStorage, String promptMessage) {
		int liqueurId;
		boolean result;
		if (promptMessage == null || promptMessage.isEmpty()) {
			promptMessage = "수정할 주류의 ID를 입력하세요 : ";
		}
		do {
			System.out.print(promptMessage);
			liqueurId = readNumber();
			result = liqueurStorage.isValidLiqueur(liqueurId);
			if (!result)
				System.out.print("잘못된 주류의 ID입니다.");
		} while (!result);

		return liqueurId;
	}

	public int selectLiqueurId(Cart cart, String promptMessage) {
		int liqueurId;
		boolean result;
		do {
			System.out.print(promptMessage);
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

		String phoneNumber;
		while (true) {
			System.out.print(">> 전화번호 : ");
			phoneNumber = input.nextLine();
			if (phoneNumber.matches("\\d+")) {
				break;
			} else {
				System.out.println("유효한 전화번호를 입력하세요. 숫자만 입력할 수 있습니다.");
			}
		}
		customer.setPhone(phoneNumber);
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
	    System.out.print("검색할 주류 이름 또는 종류(한글 또는 영어로 3글자 이상 입력하세요) : ");
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
	public int inputLiqueurId() {
		System.out.print("주류 ID: ");
		return readNumber();
	}

	public String inputLiqueurName() {
		Scanner input = new Scanner(System.in);
		System.out.print("주류 이름: ");
		return input.nextLine();
	}

	public String inputLiqueurTypeCategory() {
		Scanner input = new Scanner(System.in);
		System.out.print("주류 종류-카테고리: ");
		return input.nextLine();
	}

	public double inputAlcoholContent() {
		Scanner input = new Scanner(System.in);
		double alcoholContent = 0;
		boolean valid = false;
		while (!valid) {
			try {
				System.out.print("알코올 도수: ");
				alcoholContent = Double.parseDouble(input.nextLine());
				valid = true;
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력 가능합니다.");
			}
		}
		return alcoholContent;
	}

	public String inputOrigin() {
		Scanner input = new Scanner(System.in);
		System.out.print("원산지: ");
		return input.nextLine();
	}

	public int inputVolume() {
		Scanner input = new Scanner(System.in);
		int volume = 0;
		boolean valid = false;
		while (!valid) {
			try {
				System.out.print("용량 (ml): ");
				volume = Integer.parseInt(input.nextLine());
				valid = true;
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력 가능합니다.");
			}
		}
		return volume;
	}

	public int inputPrice() {
		Scanner input = new Scanner(System.in);
		int price = 0;
		boolean valid = false;
		while (!valid) {
			try {
				System.out.print("가격 (원): ");
				price = Integer.parseInt(input.nextLine());
				valid = true;
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력 가능합니다(원).");
			}
		}
		return price;
	}

	///////////////////// 공용 모듈 ////////////////////////

	// 입력된 문자열을 입력했을 때만 true를 반환하는 confirm용
	public boolean askConfirm(String message, String yes) {
		System.out.print(message);
		Scanner input = new Scanner(System.in);
		return input.nextLine().equals(yes);
	}

	// 숫자 입력 받기 (숫자가 아닌 문자를 넣으면 예외 처리하고 다시 입력받기)
	public int readNumber() {
		Scanner input = new Scanner(System.in);
		try {
			return input.nextInt();
		} catch (Exception e) {
			System.out.print("숫자를 입력하세요 :");
			return readNumber();
		}
	}

	// 메시지를 출력하고 숫자를 입력받기
	public int readNumber(String message) {
		System.out.print(message);
		return readNumber();
	}

	// 입력된 문자열 출력
	public void showMessage(String message) {
		System.out.println(message);
	}

	// 모든 주문 보기
	public void displayAllOrders(ArrayList<String> orders) {
		System.out.println("***** 모든 주문 보기 ******");
		for (String order : orders) {
			System.out.println(order);
		}
	}
}