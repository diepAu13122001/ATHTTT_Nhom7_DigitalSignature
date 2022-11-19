package cart;

import model.Product;

public class ShoppingCartItem<T extends Item> {
	private T item;
	private int quantity;

    public ShoppingCartItem(T anItem) {
        item = anItem;
        quantity = 1;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public T getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
    public double totalPrice() {
    	Product p = (Product)item;
    	return getQuantity()*p.getPrice();
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
