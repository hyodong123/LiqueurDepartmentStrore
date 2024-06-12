package LiqueurDepartment.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OrderNumberManager {
    private static final String ORDER_NUMBER_FILE_PATH = "ordernumber.txt";
    
    public static int getNextOrderNumber() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_NUMBER_FILE_PATH))) {
            String line = reader.readLine();
            int orderNumber = Integer.parseInt(line.trim());
            orderNumber++;
            writeOrderNumber(orderNumber); // 증가된 주문 번호를 다시 파일에 씀
            return orderNumber;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static void writeOrderNumber(int orderNumber) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_NUMBER_FILE_PATH))) {
            writer.write(String.valueOf(orderNumber));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
