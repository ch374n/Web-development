package com.nimbalkar.chetan;
import java.util.List;
import java.util.ArrayList;

public class Cart {
    private static Cart cart = new Cart();
    private int userID = -1;
    private List<Book> books = new ArrayList<>();
    private DataController controller = new DataController();
    
    public static Cart getCart() {
        return cart;
    }
    
    public void addToCart(String b) {
        if(userID != -1) {
            userID = User.getUserID();
        }
        books.add(controller.getBook(b));
    }
    
    public void setCartItems(List<Book> books) {
        this.books = books;
    }
    public List<Book> getCartItems() {
        return books;
    }
}
