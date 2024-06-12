package LiqueurDepartment.model;

public class CartItem {
    private Liqueur liqueur;
    private int quantity;

    public CartItem(Liqueur liqueur) {
        this.liqueur = liqueur;
        this.quantity = 1;
    }

    public Liqueur getLiqueur() {
        return liqueur;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public int getTotalPrice() {
        return liqueur.getPrice() * quantity;
    }
}
