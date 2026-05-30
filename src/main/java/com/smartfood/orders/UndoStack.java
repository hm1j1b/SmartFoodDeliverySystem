package com.smartfood.orders;

// Stack implementation using a Linked List (LIFO)
public class UndoStack {
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