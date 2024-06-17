package liqueurDepartment;

import java.io.IOException;

import liqueurDepartment.controller.LiqueurDepartmentController;

public class LiqueurDepartment {

    public static Object user;

	public static void main(String[] args) throws IOException {
        // model 생성
        liqueurDepartment.model.LiqueurStorage liqueurStorage = new liqueurDepartment.model.LiqueurStorage();
        liqueurDepartment.model.Cart cart = new liqueurDepartment.model.Cart();
        
        // view 생성
        liqueurDepartment.view.ConsoleView view = new liqueurDepartment.view.ConsoleView();
        
        // controller 생성 (model, view)
        LiqueurDepartmentController controller = new LiqueurDepartmentController(liqueurStorage, cart, view);
        controller.start();
    }
}