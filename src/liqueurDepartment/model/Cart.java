package liqueurDepartment.model;

import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> itemList = new ArrayList<>();

    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    public ArrayList<CartItem> getItemList() {
        return itemList;
    }

    public int getNumItems() {
        return itemList.size();
    }

    public String getItemInfo(int index) {
        return itemList.get(index).toString();
    }

    public void addItem(Liqueur liqueur) {
        CartItem item = getCartItem(liqueur);
        if (item == null) {
            itemList.add(new CartItem(liqueur));
        } else {
            item.addQuantity(1);
        }
    }

    private CartItem getCartItem(Liqueur liqueur) {
        for (CartItem item : itemList) {
            if (item.getLiqueur() == liqueur) return item;
        }
        return null;
    }

    private CartItem getCartItem(int liqueurId) {
        for (CartItem item : itemList) {
            if (item.liqueurId == liqueurId) return item;
        }
        return null;
    }

    public void resetCart() {
        itemList.clear();
    }

    public boolean isValidItem(int liqueurId) {
        for (CartItem item : itemList) {
            if (item.liqueurId == liqueurId) return true;
        }
        return false;
    }

    public void deleteItem(int liqueurId) {
        CartItem item = getCartItem(liqueurId);
        if (item != null) {
            itemList.remove(item);
        }
    }

    public void updateQuantity(int liqueurId, int quantity) {
        if (quantity == 0) {
            deleteItem(liqueurId);
        } else {
            CartItem item = getCartItem(liqueurId);
            if (item != null) {
                item.setQuantity(quantity);
            }
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (CartItem item : itemList) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}
