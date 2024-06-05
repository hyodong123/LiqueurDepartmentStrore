package LiqueurDepartment.model;

public class Order {
    private int orderId;
    private Customer customer;
    private Cart cart;

    public Order(int orderId, Customer customer, Cart cart) {
        this.orderId = orderId;
        this.customer = customer;
        this.cart = cart;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
