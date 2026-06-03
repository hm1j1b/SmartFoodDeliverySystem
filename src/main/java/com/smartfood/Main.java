package com.smartfood;

import com.smartfood.delivery.DeliverySystem;
import com.smartfood.orders.OrderSystem;
import com.smartfood.retrieval.DataHashMap;
import com.smartfood.routes.Dijkstra;
import com.smartfood.routes.Graph;
import com.smartfood.search.AVLTree;
import com.smartfood.users.Restaurant;
import com.smartfood.users.RestaurantLinkedList;
import com.smartfood.users.User;
import com.smartfood.users.UserLinkedList;

public class Main {
    public static void main(String[] args) {
        UserLinkedList userList = new UserLinkedList();
        RestaurantLinkedList restaurantList = new RestaurantLinkedList();
        OrderSystem orderSystem = new OrderSystem();
        DeliverySystem deliverySystem = new DeliverySystem(10);
        Graph graph = new Graph();
        Dijkstra dijkstra = new Dijkstra(graph);
        AVLTree foodTree = new AVLTree();
        DataHashMap dataHashMap = new DataHashMap();

        System.out.println("========================================");
        System.out.println("   SMART FOOD DELIVERY SYSTEM DEMO");
        System.out.println("========================================");

        System.out.println("\n=== 1. USER & RESTAURANT MANAGEMENT ===");

        User user1 = new User("U001", "John Ali", "012-3456789");
        User user2 = new User("U002", "Natsuki Daniel", "011-9876543");
        User user3 = new User("U003", "Kitasan Black", "019-715 4361");

        userList.addUser(user1.getId(), user1.getName(), user1.getContactInfo());
        userList.addUser(user2.getId(), user2.getName(),user2.getContactInfo());
        userList.addUser(user3.getId(), user3.getName(), user3.getContactInfo());
        
        Restaurant restaurant1 = new Restaurant("R001", "Oguri Eats", "Klang");
        Restaurant restaurant2 = new Restaurant("R002", "[Title Card]", "Abydos");
        Restaurant restaurant3 = new Restaurant("R003", "Kirky's", "Gensokyo");

        restaurantList.addRestaurant(restaurant1);
        restaurantList.addRestaurant(restaurant2);
        restaurantList.addRestaurant(restaurant3);

        userList.displayUsers();
        restaurantList.displayRestaurants();

        dataHashMap.addUser(user1);
        dataHashMap.addUser(user2);
        dataHashMap.addUser(user3);

        dataHashMap.addRestaurant(restaurant1);
        dataHashMap.addRestaurant(restaurant2);
        dataHashMap.addRestaurant(restaurant3);
    }
}
