package com.smartfood.users;

public class RestaurantLinkedList {
    private RestaurantNode head;
    private int size;

    // Constructor
    public RestaurantLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Method to add a restaurant to the linked list
    public void addRestaurant(Restaurant restaurant) {
        RestaurantNode newNode = new RestaurantNode(restaurant);
        if (head == null) {
            head = newNode;
        } else {
            RestaurantNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
        System.out.println("Restaurant '" + restaurant.getName() + "' added successfully!");
    }

    // Remove a restaurant by ID
    public void removeRestaurant(String id) {
        RestaurantNode current = head;
        RestaurantNode previous = null;

        while (current != null) {
            if (current.getRestaurant().getId().equals(id)) {
                if (previous != null) {
                    previous.setNext(current.getNext());
                } else {
                    head = current.getNext();
                }
                size--;
                System.out.println("Restaurant ID " + id + " removed successfully!");
                return;
            }
            previous = current;
            current = current.getNext();
        }
        System.out.println("Restaurant ID " + id + " not found!");
    }

    // Search a restaurant by ID
    public Restaurant searchRestaurant(String id){
        RestaurantNode current = head;
        while (current != null) {
            if(current.getRestaurant().getId().equals(id)){
                System.out.println("Restaurant found: " +  current.getRestaurant());
                return current.getRestaurant();
            }
            current = current.getNext();
        }
        System.out.println("Restaurant ID " + id + " not found!");
        return null;
    }

    // Display all restaurants 
    public void displayRestaurants() {
        if (head == null) {
            System.out.println("No restaurants in the system.");
            return;
        }
        System.out.println("\n--- Restaurant List (" + size + " restaurant(s)) ---");
        RestaurantNode current = head;
        while (current != null) {
            System.out.println(current.getRestaurant());
            current = current.getNext();
        }
        System.out.println("------------------------------------------\n");
    }
}

