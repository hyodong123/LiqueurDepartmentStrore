package LiqueurDepartment.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderWriter {
    private static final String ORDER_FILE_PATH = "orderlist.txt";

    public static void writeOrder(Order order) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ORDER_FILE_PATH, true))) {
            writer.println("***** 주문할 주류 *****");
            for (CartItem item : order.getCart().getItemList()) {
                writer.println(item.getLiqueur().toString());
            }
            writer.println("총 금액 : " + order.getCart().getTotalPrice() + "원");
            writer.println("***** 배송 정보 *****");
            writer.println(">> 이름 : " + order.getCustomer().getName());
            writer.println(">> 전화번호 : " + order.getCustomer().getPhone());
            writer.println(">> 이메일 : " + order.getCustomer().getEmail());
            writer.println(">> 주소 : " + order.getCustomer().getAddress());
            writer.println();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
