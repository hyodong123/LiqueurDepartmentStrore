package liqueurDepartment.order;

import LiqueurDepartment.model.Customer;
import liqueurDepartment.model.Cart;

public class Order {
    private int orderId;
    private Customer customer;
    private Cart cart;

    public Order(int orderId, Customer customer, Cart cart) {
        this.orderId = orderId;
        this.customer = customer;
        this.cart = cart;
    }

    // getter와 setter 추가
    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return String.format("주문 ID: %d, 고객: %s, 총 금액: %d원", orderId, customer.getName(), cart.getTotalPrice());
    }
}