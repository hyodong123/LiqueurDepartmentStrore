package LiqueurDepartment.controller;

import java.util.ArrayList;

import LiqueurDepartment.model.Admin;
import LiqueurDepartment.model.Cart;
import LiqueurDepartment.model.CartItem;
import LiqueurDepartment.model.Customer;
import LiqueurDepartment.model.Liqueur;
import LiqueurDepartment.model.LiqueurStorage;
import LiqueurDepartment.order.OrderNumberManager;
import LiqueurDepartment.order.OrderReader;
import LiqueurDepartment.order.OrderWriter;
import LiqueurDepartment.view.ConsoleView;

public class LiqueurDepartmentController {
    private ConsoleView view;
    private LiqueurStorage mLiqueurStorage;
    private Cart mCart;
    private Customer mCustomer;
    private boolean isAdminMode = false;

    private static final String ORDERS_FILE_PATH = "orders.txt"; // 실제 파일 경로로 수정

    String[] mainMenuList = { "0. 종료", "1. 로그인", "2. 주류 정보 보기", "3. 장바구니 보기", "4. 장바구니에 주류 추가", "5. 장바구니 주류 삭제",
            "6. 장바구니 주류 수량 변경", "7. 장바구니 비우기", "8. 이름 또는 종류로 주류 검색", "9. 주문" };

    String[] adminMenuList = { "0. 종료", "1. 주류 정보 보기", "2. 주류 추가", "3. 주류 삭제", "4. 주류 수정", "5. 모든 주문 보기", "6. 출고 처리", "7. 로그아웃" };

    public LiqueurDepartmentController(LiqueurStorage liqueurStorage, Cart cart, ConsoleView view) {
        this.view = view;
        this.mLiqueurStorage = liqueurStorage;
        this.mCart = cart;
    }

    public void start() {
        welcome();

        int menu;

        do {
            menu = view.selectMenu(isAdminMode ? adminMenuList : mainMenuList);

            if (isAdminMode) {
                switch (menu) {
                case 1 -> viewLiqueurInfo();
                case 2 -> addLiqueur();
                case 3 -> deleteLiqueur();
                case 4 -> updateLiqueur();
                case 5 -> viewAllOrders();
                case 6 -> processOrder();
                case 7 -> logout();
                case 0 -> end();
                default -> view.showMessage("잘못된 메뉴 번호입니다.");
                }
            } else {
                switch (menu) {
                case 1 -> login();
                case 2 -> viewLiqueurInfo();
                case 3 -> viewCart();
                case 4 -> addLiqueur2Cart();
                case 5 -> deleteLiqueurInCart();
                case 6 -> updateLiqueurInCart();
                case 7 -> resetCart();
                case 8 -> searchByNameOrType();
                case 9 -> order();
                case 0 -> end();
                default -> view.showMessage("잘못된 메뉴 번호입니다.");
                }
            }
        } while (menu != 0);
    }

    private void login() {
        String id = view.inputAdminId();
        String password = view.inputAdminPassword();
        if (Admin.isAdmin(id, password)) {
            isAdminMode = true;
            view.showMessage(">> 관리자 모드로 로그인되었습니다.");
        } else {
            view.showMessage(">> 로그인 실패: 잘못된 관리자 ID 또는 비밀번호입니다.");
        }
    }

    private void logout() {
        isAdminMode = false;
        view.showMessage(">> 로그아웃되었습니다.");
    }

    private void welcome() {
        view.displayWelcome();
    }

    private void registerCustomerInfo() {
        mCustomer = new Customer();
        view.inputCustomerInfo(mCustomer);
    }

    private void viewLiqueurInfo() {
        view.displayLiqueurInfo(mLiqueurStorage);
    }

    private void viewCart() {
        if (mCart.isEmpty()) {
            view.showMessage(">> 장바구니가 비어 있습니다.");
        } else {
            view.displayCart(mCart);
        }
    }

    private void addLiqueur2Cart() {
        view.displayLiqueurInfo(mLiqueurStorage);
        int liqueurId = view.selectLiqueurId(mLiqueurStorage, "추가할 주류의 ID를 입력하세요 : ");
        mCart.addItem(mLiqueurStorage.getLiqueurById(liqueurId));
        view.showMessage(">> 장바구니에 주류를 추가하였습니다.");
    }

    private void deleteLiqueurInCart() {
        if (mCart.isEmpty()) {
            view.showMessage(">> 장바구니가 비어 있습니다.");
        } else {
            view.displayCart(mCart);
            int liqueurId = view.selectLiqueurId(mCart, "삭제할 주류의 ID를 입력하세요 : ");
            if (view.askConfirm(">> 해당 주류를 삭제하려면 yes를 입력하세요 : ", "yes")) {
                mCart.deleteItem(liqueurId);
                view.showMessage(">> 해당 주류를 삭제했습니다.");
            }
        }
    }

    private void deleteLiqueur() {
        view.displayLiqueurInfo(mLiqueurStorage);
        int liqueurId = view.selectLiqueurId(mLiqueurStorage, "삭제할 주류의 ID를 입력하세요 : ");
        if (mLiqueurStorage.deleteLiqueur(liqueurId)) {
            view.showMessage(">> 주류가 삭제되었습니다.");
        } else {
            view.showMessage(">> 주류 삭제에 실패하였습니다.");
        }
    }
    private void updateLiqueurInCart() {
        if (mCart.isEmpty()) {
            view.showMessage(">> 장바구니가 비어 있습니다.");
        } else {
            view.displayCart(mCart);
            int liqueurId = view.selectLiqueurId(mCart, "수정할 주류의 ID를 입력하세요 : ");
            int quantity = view.inputQuantity(0, mLiqueurStorage.getMaxQuantity());
            mCart.updateQuantity(liqueurId, quantity);
            view.showMessage(">> 주류 수량이 수정되었습니다.");
        }
    }

    private void resetCart() {
        if (mCart.isEmpty()) {
            view.showMessage(">> 장바구니가 비어 있습니다.");
        } else {
            view.displayCart(mCart);
            if (view.askConfirm(">> 장바구니를 비우려면 yes를 입력하세요 : ", "yes")) {
                mCart.resetCart();
                view.showMessage(">> 장바구니를 비웠습니다.");
            }
        }
    }

    private void searchByNameOrType() {
        String keyword = view.inputSearchKeyword();
        if (keyword.length() >= 3) {
            var results = mLiqueurStorage.searchLiqueurByNameOrType(keyword);
            view.displaySearchResults(results);
        } else {
            view.showMessage("검색어는 3글자 이상이어야 합니다.");
        }
    }

    private void order() {
        if (mCart.isEmpty()) {
            view.showMessage(">> 장바구니가 비어 있습니다. 주문을 진행할 수 없습니다.");
            return;
        }

        if (mCustomer == null) {
            registerCustomerInfo();
        }

        getDeliveryInfo();
        viewOrderInfo();
        if (view.askConfirm("진짜 주문하려면 yes를 입력하세요 : ", "yes")) {
            if (mCart.getItems().isEmpty()) {
                view.showMessage("장바구니가 없습니다.");
                return;
            }

            int orderId = OrderNumberManager.getNextOrderNumber();  // 주문 ID를 가져옵니다.

            StringBuilder orderDetails = new StringBuilder();
            orderDetails.append(String.format("주문 ID: %d, 고객: %s, 총 금액: %d원, 주소: %s, 전화번호: %s, 이메일: %s, ",
                    orderId,
                    mCustomer.getName(),
                    mCart.getTotalPrice(),
                    mCustomer.getAddress(),
                    mCustomer.getPhone(),
                    mCustomer.getEmail()
            ));

            for (CartItem item : mCart.getItems()) {
                orderDetails.append(String.format("주류: %s, 수량: %d, ", item.getLiqueur().getName(), item.getQuantity()));
            }

            OrderWriter.writeOrder(ORDERS_FILE_PATH, orderDetails.toString());
            mCart.resetCart();  // 장바구니를 비웁니다.
            view.showMessage(">> 주문이 완료되었습니다.");

            // 확인용 메시지 출력
            view.displayCart(mCart);

            OrderNumberManager.saveOrderNumber();  // 주문 번호를 저장합니다.
        }
    }

    private void getDeliveryInfo() {
        view.inputDeliveryInfo(mCustomer);
    }

    private void viewOrderInfo() {
        view.displayOrder(mCart, mCustomer);
    }

    private void end() {
        view.showMessage(">> LiqueurDepartment을 종료합니다.");
    }

    // 관리자 모드 기능
    private void addLiqueur() {
        int nextLiqueurId = mLiqueurStorage.getNextLiqueurId();  // nextLiqueurId를 가져옵니다.
        String name = view.inputLiqueurName();
        String typeCategory = view.inputLiqueurTypeCategory();
        double alcoholContent = view.inputAlcoholContent();
        String origin = view.inputOrigin();
        int volume = view.inputVolume();
        int price = view.inputPrice();

        Liqueur liqueur = new Liqueur(nextLiqueurId, name, typeCategory, alcoholContent, origin, volume, price);
        mLiqueurStorage.addLiqueur(liqueur);  // liqueur 객체에 nextLiqueurId가 설정됩니다.
        view.showMessage(">> 주류가 추가되었습니다.");
    }

    private void updateLiqueur() {
        view.displayLiqueurInfo(mLiqueurStorage);
        int liqueurId = view.selectLiqueurId(mLiqueurStorage, null); // null을 전달하여 기본 메시지를 사용

        String name = view.inputLiqueurName();
        String typeCategory = view.inputLiqueurTypeCategory();
        double alcoholContent = view.inputAlcoholContent();
        String origin = view.inputOrigin();
        int volume = view.inputVolume();
        int price = view.inputPrice();

        Liqueur updatedLiqueur = new Liqueur(liqueurId, name, typeCategory, alcoholContent, origin, volume, price);
        if (mLiqueurStorage.updateLiqueur(liqueurId, updatedLiqueur)) {
            view.showMessage(">> 주류 정보가 수정되었습니다.");
        } else {
            view.showMessage(">> 주류 정보 수정에 실패하였습니다.");
        }
    }

    private void viewAllOrders() {
        ArrayList<String> orders = OrderReader.readOrders(ORDERS_FILE_PATH);
        if (orders.isEmpty()) {
            view.showMessage("주문 내역이 없습니다.");
        } else {
            view.displayAllOrders(orders);
        }
    }

    private void processOrder() {
        ArrayList<String> orders = OrderReader.readOrders(ORDERS_FILE_PATH);
        if (orders.isEmpty()) {
            view.showMessage("주문 내역이 없습니다.");
            return; // 주문 내역이 없으므로 더 이상 진행하지 않음
        }
        
        view.displayAllOrders(orders);
        int orderId = view.readNumber("출고 처리할 주문의 ID를 입력하세요 : ");
        
        boolean found = false;
        for (String order : orders) {
            if (order.contains("주문 ID: " + orderId + ",")) {
                orders.remove(order);
                found = true;
                break;
            }
        }
        
        if (found) {
            OrderWriter.overwriteOrders(ORDERS_FILE_PATH, orders);
            view.showMessage(">> 주문이 출고 처리되었습니다.");
        } else {
            view.showMessage(">> 해당 주문 ID를 찾을 수 없습니다.");
        }
    }
}