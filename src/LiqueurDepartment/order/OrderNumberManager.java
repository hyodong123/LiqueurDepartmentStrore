package LiqueurDepartment.order;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OrderNumberManager {
    private static final String ORDER_NUMBER_FILE_PATH = "ordernumber.txt";
    private static int currentOrderNumber = 1;  // 초기값을 1로 설정

    static {
        loadOrderNumber();
    }

    private static void loadOrderNumber() {
        File file = new File(ORDER_NUMBER_FILE_PATH);
        if (!file.exists()) {
            saveOrderNumber(); // 초기 파일 생성
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_NUMBER_FILE_PATH))) {
            String line;
            int maxOrderNumber = 0;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                try {
                    int orderNumber = Integer.parseInt(line.trim());
                    if (orderNumber > maxOrderNumber) {
                        maxOrderNumber = orderNumber;
                    }
                } catch (NumberFormatException e) {
                    // 잘못된 형식의 주문 ID를 무시합니다.
                }
            }
            currentOrderNumber = maxOrderNumber + 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getNextOrderNumber() {
        int nextOrderNumber = currentOrderNumber++;
        saveOrderNumber();
        return nextOrderNumber;
    }

    public static void saveOrderNumber() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_NUMBER_FILE_PATH))) {
            writer.write(String.valueOf(currentOrderNumber));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
