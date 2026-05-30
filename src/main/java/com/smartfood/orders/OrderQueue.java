package com.smartfood.orders;

// Queue implementation using a Linked List (FIFO)
public class OrderQueue {
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