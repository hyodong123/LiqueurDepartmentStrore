package LiquerDepartment;

import java.io.IOException;

import LiqueurDepartment.controller.LiqueurDepartmentController;
import LiqueurDepartment.model.Cart;
import LiqueurDepartment.model.LiqueurStorage;
import LiqueurDepartment.view.ConsoleView;

public class LiqueurDepartment {

    public static void main(String[] args) throws IOException {
        // model 생성
        LiqueurStorage liqueurStorage = new LiqueurStorage();
        Cart cart = new Cart();
        
        // view 생성
        ConsoleView view = new ConsoleView();
        
        // controller 생성 (model, view)
        LiqueurDepartmentController controller = new LiqueurDepartmentController(liqueurStorage, cart, view);
        controller.start();
    }
}