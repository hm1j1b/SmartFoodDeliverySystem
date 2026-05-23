package com.smartfood.users;

public class UserLinkedList {
    private UserNode head;
    private int count;

    // Constructor
    public UserLinkedList(UserNode head, int count) {
        this.head = head;
        this.count = 0;
    }

    // Add new user at the head of list
    public void addUser(String id, String name, String contactInfo){
        User user = new User(id, name, contactInfo);
        UserNode newNode = new UserNode(user);

        newNode.setNext(head);
        head = newNode;
        count++;
        System.out.println("User: " + name + " added successfully!");
    }

    // Delete user by ID
    public void removeUser(String id){
        UserNode current = head;
        UserNode previous = null;

        while (current != null) {
            if (current.getUser().getId().equals(id)) {
                if (previous != null) {
                    previous.setNext(current.getNext());
                } else {
                    head = current.getNext();
                }
                count--;
                System.out.println("User ID " + id + " removed successfully!");
                return;
            }
            previous = current;
            current = current.getNext();
        }
        System.out.println("User ID " + id + " not found.");
    }
    
    // Search user by ID
    public User searchUser(String id) {
        UserNode current = head;
        while (current != null) {
            if (current.getUser().getId().equals(id)) {
                System.out.println("User found: " + current.getUser());
                return current.getUser();
            }
            current = current.getNext();
        }
        System.out.println("User ID " + id + " not found.");
        return null;
    }
    
    // Display all users
     public void displayUsers() {
        if (head == null) {
            System.out.println("No users in the system.");
            return;
        }
        System.out.println("\n--- User List (" + count + " user(s)) ---");
        UserNode current = head;
        while (current != null) {
            System.out.println(current.getUser());
            current = current.getNext();
        }
        System.out.println("-----------------------------------\n");
    }
}
