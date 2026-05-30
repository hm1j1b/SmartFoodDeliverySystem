package com.smartfood.orders;

// Represents an individual food order node in our linked structures
public class OrderNode {
    // Made public so other components (like your friend's HashMap) can access them
    public String orderId;
    public String foodItem;
    public double price;
    public OrderNode next; 

    public OrderNode(String orderId, String foodItem, double price) {
        this.orderId = orderId;
        this.foodItem = foodItem;
        this.price = price;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + " | Item: " + foodItem + " ($" + price + ")";
    }
}