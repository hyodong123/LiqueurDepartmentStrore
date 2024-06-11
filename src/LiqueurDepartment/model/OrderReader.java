package LiqueurDepartment.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OrderReader {
	private static final String ORDER_NUMBER_FILE_PATH = "C:\\_Lecture\\LiqueurDepartmentStore\\ordernumber.txt";

	public static ArrayList<Order> readOrders() {
		ArrayList<Order> orders = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_NUMBER_FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// 주문 정보를 파싱하여 Order 객체로 만들어 리스트에 추가
				orders.add(parseOrder(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return orders;
	}

	private static Order parseOrder(String line) {
		// 주문 정보와 배송 정보를 줄바꿈 문자(\n)로 구분하여 분리
		String[] parts = line.split("\n");

		// 주문 정보 파싱
		String orderInfo = parts[0]; // 주문 정보는 첫 번째 줄에 있음
		String[] orderDetails = orderInfo.split(", "); // 콤마로 주문 정보를 분리
		int orderId = Integer.parseInt(orderDetails[0]); // 주문 번호
		String liqueurName = orderDetails[1]; // 주류 이름
		int quantity = Integer.parseInt(orderDetails[2].split(" ")[0]); // 수량
		int price = Integer.parseInt(orderDetails[3].replaceAll("[^0-9]", "")); // 가격

		// 배송 정보 파싱
		String customerInfo = parts[1]; // 배송 정보는 두 번째 줄에 있음
		String[] customerDetails = customerInfo.split(": "); // 콜론으로 배송 정보를 분리
		String customerName = customerDetails[1]; // 이름
		String customerPhone = customerDetails[2]; // 전화번호
		String customerEmail = customerDetails[3]; // 이메일
		String customerAddress = customerDetails[4]; // 주소

		// Order 객체 생성하여 반환
		return new Order(orderId, new Customer(customerName, customerPhone, customerEmail, customerAddress),
				new Cart());
	}
}