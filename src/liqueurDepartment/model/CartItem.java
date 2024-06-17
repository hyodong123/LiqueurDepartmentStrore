package liqueurDepartment.model;

public class CartItem {
    Liqueur liqueur;
    int liqueurId;
    int quantity;

    public CartItem(Liqueur liqueur) {
        this.liqueur = liqueur;
        this.liqueurId = liqueur.getLiqueurId();
        this.quantity = 1;
    }

    public Liqueur getLiqueur() {
        return liqueur;
    }

    public void setLiqueur(Liqueur liqueur) {
        this.liqueur = liqueur;
        this.liqueurId = liqueur.getLiqueurId();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return liqueur.getLiqueurId() + ", " + liqueur.getLiqueurName() + ", " + quantity + "병, " + getPrice() + "원";
    }

    public int getPrice() {
        return quantity * liqueur.getPrice();
    }
}
