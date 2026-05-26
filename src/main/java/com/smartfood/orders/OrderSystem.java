package com.smartfood.orders;

// Represents an individual food order node in our linked structures
class OrderNode {
    String orderId;
    String foodItem;
    double price;
    OrderNode next; 

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

// Queue implementation using a Linked List (FIFO)
class OrderQueue {
    private OrderNode head; 
    private OrderNode tail; 

    public void enqueue(OrderNode newOrder) {
        if (tail == null) {
            head = newOrder;
            tail = newOrder;
        } else {
            tail.next = newOrder;
            tail = newOrder;
        }
        System.out.println("[Queue Update] " + newOrder.foodItem + " pushed to kitchen queue.");
    }

    public OrderNode dequeue() {
        if (head == null) return null;
        OrderNode processedOrder = head;
        head = head.next;
        if (head == null) tail = null; 
        return processedOrder;
    }

    // Safely removes the latest added item from the tail if an Undo occurs
    public void removeLastAdded() {
        if (head == null) return;
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        OrderNode current = head;
        while (current.next != tail) {
            current = current.next;
        }
        current.next = null;
        tail = current;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

// Stack implementation using a Linked List (LIFO)
class UndoStack {
    private OrderNode top; 

    public void push(OrderNode order) {
        OrderNode historyNode = new OrderNode(order.orderId, order.foodItem, order.price);
        historyNode.next = top;
        top = historyNode;
    }

    public OrderNode pop() {
        if (top == null) return null;
        OrderNode popped = top;
        top = top.next;
        return popped;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

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