package liqueurDepartment.order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrderWriter {
    public static void writeOrder(String filePath, String orderDetails) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(orderDetails);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void overwriteOrders(String filePath, List<String> orders) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String order : orders) {
                bw.write(order);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}