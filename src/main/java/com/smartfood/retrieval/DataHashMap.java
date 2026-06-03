package com.smartfood.retrieval;

import java.util.HashMap;

import com.smartfood.orders.OrderNode;
import com.smartfood.users.Restaurant;
import com.smartfood.users.User;

public class DataHashMap {
    HashMap<String, User> userMap;
    HashMap<String, Restaurant> restaurantMap;
    HashMap<String, OrderNode> orderMap;

    public DataHashMap() {
        userMap = new HashMap<>();
        restaurantMap = new HashMap<>();
        orderMap = new HashMap<>();
    }

    // Users
    public void addUser(User user) {
        userMap.put(user.getId(), user);
    }

    public User getUser(String id) {
        return userMap.get(id);
    }

    public void removeUser(String id) {
        User user = userMap.remove(id);
        if (user == null) {
            System.out.println("User " + id + " not found.");
        }
        else {
            System.out.println("User " + id + " remove successfully.");
        }
    }

    public void displayUsers() {
        if (userMap.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        for (User user : userMap.values()) {
            System.out.println(user);
        }
    }

    // Restaurant
    public void addRestaurant(Restaurant restaurant) {
        restaurantMap.put(restaurant.getId(), restaurant);
    }

    public Restaurant getRestaurant(String id) {
        return restaurantMap.get(id);
    }

    public void removeRestaurant(String id) {
        Restaurant restaurant = restaurantMap.remove(id);

        if (restaurant == null) {
            System.out.println("Restaurant " + id + " not found.");
        }
        else {
            System.out.println("Restaurant " + id + " remove successfully.");
        }
    }

    public void displayRestaurants() {
        if (restaurantMap.isEmpty()) {
            System.out.println("No restaurants found.");
            return;
        }
        for (Restaurant restaurant : restaurantMap.values()) {
            System.out.println(restaurant);
        }
    }

    // Orders
    public void addOrder(OrderNode order) {
        orderMap.put(order.orderId, order);
    }

    public OrderNode getOrder(String id) {
        return orderMap.get(id);
    }

    public void removeOrder(String id) { 
        OrderNode order = orderMap.remove(id);

        if (order == null) {
            System.out.println("Order " + id + " not found.");
        }
        else {
            System.out.println("Order " + id + " remove successfully.");
        }
    }

    public void displayOrders() {
        if (orderMap.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        for (OrderNode order : orderMap.values()) {
            System.out.println(order);
        }
    }
}
