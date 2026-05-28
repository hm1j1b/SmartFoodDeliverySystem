package com.smartfood.users;

public class RestaurantNode {

    private Restaurant restaurant;
    private RestaurantNode next;

    // Constructors
    public RestaurantNode(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.next = null;
    }

    // Setters and Getters
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public RestaurantNode getNext() {
        return next;
    }
    public void setNext(RestaurantNode next) {
        this.next = next;
    }
    
}
