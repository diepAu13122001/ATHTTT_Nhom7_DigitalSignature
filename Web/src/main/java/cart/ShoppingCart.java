package cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart<ID, T extends Item> {
    private Map<ID, ShoppingCartItem<T>> items = null;
    private int numberOfItems = 0;
    public ShoppingCart() {
        items = new HashMap<ID, ShoppingCartItem<T>>();
      
    }
    
    public synchronized void add(ID id, T item) {
        if (items.containsKey(id)) {
            ShoppingCartItem<T> scitem = items.get(id);
            scitem.incrementQuantity();
            System.out.println("in add, quantity is " + scitem.getQuantity());
        } else {
            ShoppingCartItem<T> newScitem = new ShoppingCartItem<T>(item);
            items.put(id, newScitem);
            System.out.println("in add, quantity is " + newScitem.getQuantity());
        }
        numberOfItems++;
    }
    public synchronized void updateQuantity(ID id, int quantiy) { 
            ShoppingCartItem<T> scitem = items.get(id);
            scitem.setQuantity(quantiy);     
            numberOfItems= getNumberOfItems();
    }

    public synchronized void remove(String bookId) {
        if (items.containsKey(bookId)) {
            ShoppingCartItem<T> scitem = items.get(bookId);
            scitem.decrementQuantity();
            if (scitem.getQuantity() <= 0) {
                items.remove(bookId);
            }
            numberOfItems--;
        }
    }
    public synchronized void removeAll() {

    		items.clear();
    		numberOfItems=0;
    	
    }

    public synchronized List<ShoppingCartItem<T>> getCartItems() {
        List<ShoppingCartItem<T>> results = new ArrayList<ShoppingCartItem<T>>();
        results.addAll(this.items.values());
        return results;
    }
    public synchronized ShoppingCartItem<T> getCartItem(String id) {
        return this.items.get(id);
    }
    protected void finalize() throws Throwable {
        items.clear();
    }
    
    public synchronized int getNumberOfItems() {
        numberOfItems = 0;
        for (ShoppingCartItem<T> scitem : items.values()) {
            numberOfItems += scitem.getQuantity();
            System.out.println("number of items is " + numberOfItems);
        }
        return numberOfItems;
    }

    public synchronized double getTotal() {
        double amount = 0.0;
        for (ShoppingCartItem<T> scitem : items.values()) {
        	T item = scitem.getItem();
            amount += (scitem.getQuantity() * item.getPrice());
        }
        return amount;
    }
    public synchronized double getTax(double percent) {
        double amount = 0.0;
        for (ShoppingCartItem<T> scitem : items.values()) {
        	T item = scitem.getItem();
            amount += (scitem.getQuantity() * item.getPrice());
        }
        return amount*percent;
    }

    private double roundOff(double x) {
        long val = Math.round(x * 100); // cents
        return val / 100.0;
    }

    public synchronized void clear() {
        System.out.println("Clearing cart.");
        items.clear();
        numberOfItems = 0;
    }
}
