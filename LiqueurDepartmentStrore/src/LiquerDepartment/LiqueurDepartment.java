package LiquerDepartment;

import java.io.IOException;

import LiquerDepartment.LiquerDepartmentController;
import LiquerDepartment.model.LiqueurStorage;
import LiquerDepartment.model.Cart;
import LiquerDepartment.view.ConsoleView;

public class LiqueurDepartment {

	public static void main(String[] args) {
		public static void main(String[] args) throws IOException {
			// model 생성
			LiqueuStorage LiqueuStorage = new LiqueuStorage();
			Cart cart = new Cart();
			
			// view 생성
			ConsoleView view = new ConsoleView();
			
			// controller 생성 (model, view)
			LiqueurDepartmentController controller = new LiqueurDepartmentController(LiqueuStorage, cart, view);
			controller.start();
	}

}
