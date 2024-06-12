package LiqueurDepartment.model;

import java.util.ArrayList;

public class Cart {
	private ArrayList<CartItem> items;

	public Cart() {
		items = new ArrayList<>();
	}

	public void addItem(Liqueur liqueur) {
		for (CartItem item : items) {
			if (item.getLiqueur().getId() == liqueur.getId()) {
				item.increaseQuantity();
				return;
			}
		}
		items.add(new CartItem(liqueur));
	}

	public void deleteItem(int liqueurId) {
		items.removeIf(item -> item.getLiqueur().getId() == liqueurId);
	}

	public void updateQuantity(int liqueurId, int quantity) {
		for (CartItem item : items) {
			if (item.getLiqueur().getId() == liqueurId) {
				item.setQuantity(quantity);
				return;
			}
		}
	}

	public boolean isValidItem(int liqueurId) {
		for (CartItem item : items) {
			if (item.getLiqueur().getId() == liqueurId) {
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public int getNumItems() {
		return items.size();
	}

	public String getItemInfo(int index) {
		CartItem item = items.get(index);
		return String.format("%d, %s, %d병, %d원", item.getLiqueur().getId(), item.getLiqueur().getName(),
				item.getQuantity(), item.getTotalPrice());
	}

	public int getTotalPrice() {
		int total = 0;
		for (CartItem item : items) {
			total += item.getTotalPrice();
		}
		return total;
	}

	public ArrayList<CartItem> getItems() {
		return items;
	}

	public void resetCart() {
		items.clear(); // 모든 항목을 제거하여 장바구니를 비웁니다.
	}

	public boolean isValidLiqueur(int liqueurId) {
		for (CartItem item : items) {
			if (item.getLiqueur().getId() == liqueurId) {
				return true;
			}
		}
		return false;
	}
}
