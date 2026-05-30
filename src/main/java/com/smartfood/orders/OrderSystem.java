package com.smartfood.orders;

// Core System coordinating the Queue and Stack operations
public class OrderSystem {
    private OrderQueue kitchenQueue = new OrderQueue();
    private UndoStack undoHistory = new UndoStack();

    public void placeOrder(String id, String item, double price) {
        System.out.println("\n>> Processing Order Placement: " + item);
        OrderNode newOrder = new OrderNode(id, item, price);
        kitchenQueue.enqueue(newOrder);
        undoHistory.push(newOrder); 
    }

    public void undoLastOrder() {
        if (undoHistory.isEmpty()) {
            System.out.println("\n>> Undo Failed: No remaining orders in your active history session.");
            return;
        }
        OrderNode undoneOrder = undoHistory.pop();
        kitchenQueue.removeLastAdded(); 
        System.out.println("\n>> Undo Triggered: " + undoneOrder.foodItem + " has been successfully recalled.");
    }

    public void processNextOrder() {
        if (kitchenQueue.isEmpty()) {
            System.out.println("\n>> Kitchen Idle: No orders pending in queue.");
            return;
        }
        OrderNode processing = kitchenQueue.dequeue();
        System.out.println("\n>> Kitchen completed cooking: " + processing);
    }

    // Simulation Runner
    public static void main(String[] args) {
        OrderSystem system = new OrderSystem();

        System.out.println("=== SYSTEM DEMO: PLACING INCOMING ORDERS ===");
        system.placeOrder("ORD001", "Nasi Lemak Ayam", 12.50);
        system.placeOrder("ORD002", "Roti Canai", 3.00);
        system.placeOrder("ORD003", "Teh Tarik", 2.50);

        System.out.println("\n=== SYSTEM DEMO: CUSTOMER UNDO REQUEST ===");
        system.undoLastOrder(); 

        System.out.println("\n=== SYSTEM DEMO: KITCHEN PROCESSING ORDERS ===");
        system.processNextOrder(); 
        system.processNextOrder(); 
        system.processNextOrder(); 
    }
}