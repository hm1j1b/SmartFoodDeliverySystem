package com.smartfood;

import com.smartfood.delivery.DeliverySystem;
import com.smartfood.orders.OrderNode;
import com.smartfood.orders.OrderSystem;
import com.smartfood.retrieval.DataHashMap;
import com.smartfood.routes.Dijkstra;
import com.smartfood.routes.Graph;
import com.smartfood.routes.Location;
import com.smartfood.search.AVLTree;
import com.smartfood.search.FoodItem;
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
        User user4 = new User("U004", "Muhammad Sumbul", "012-765 2345");

        userList.addUser(user1.getId(), user1.getName(), user1.getContactInfo());
        userList.addUser(user2.getId(), user2.getName(),user2.getContactInfo());
        userList.addUser(user3.getId(), user3.getName(), user3.getContactInfo());
        userList.addUser(user4.getId(), user4.getName(), user4.getContactInfo());
        
        Restaurant restaurant1 = new Restaurant("R001", "Mcdonald's", "Klang");
        Restaurant restaurant2 = new Restaurant("R002", "KFC", "Bukit Bintang");
        Restaurant restaurant3 = new Restaurant("R003", "Burger King", "Bangsar");
        Restaurant restaurant4 = new Restaurant("R004", "Pizza Hut", "Cheras");

        restaurantList.addRestaurant(restaurant1);
        restaurantList.addRestaurant(restaurant2);
        restaurantList.addRestaurant(restaurant3);
        restaurantList.addRestaurant(restaurant4);

        userList.displayUsers();
        restaurantList.displayRestaurants();

        dataHashMap.addUser(user1);
        dataHashMap.addUser(user2);
        dataHashMap.addUser(user3);
        dataHashMap.addUser(user4);

        dataHashMap.addRestaurant(restaurant1);
        dataHashMap.addRestaurant(restaurant2);
        dataHashMap.addRestaurant(restaurant3);
        dataHashMap.addRestaurant(restaurant4);

        System.out.println("\n=== 2. ORDER PROCESSING ===");

        OrderNode order1 = new OrderNode("O001", "Chicken Wings", 6.00);
        OrderNode order2 = new OrderNode("O002", "Beef Burger", 5.50);
        OrderNode order3 = new OrderNode("O003", "Hawaiian Pizza", 10.00);

        orderSystem.placeOrder(order1.orderId, order1.foodItem, order1.price);
        orderSystem.placeOrder(order2.orderId, order2.foodItem, order2.price);
        orderSystem.placeOrder(order3.orderId, order3.foodItem, order3.price);

        orderSystem.undoLastOrder();

        orderSystem.processNextOrder();

        dataHashMap.addOrder(order1);
        dataHashMap.addOrder(order2);
        dataHashMap.addOrder(order3);

        System.out.println("\n=== 3. DELIVERY ASSIGNMENT ===");

        deliverySystem.registerRider("R001", "Eren", 3.5);
        deliverySystem.registerRider("R002", "Violet", 5.2);
        deliverySystem.registerRider("R003", "Mark", 6.7);
        deliverySystem.registerRider("R004", "Sean", 10.1);

        deliverySystem.showAvailableRiders();

        deliverySystem.assignRiderToOrder("O001");
        deliverySystem.assignRiderToOrder("O002");

        System.out.println("\n=== 4. ROUTE OPTIMIZATION ===");

        Location loc1 = graph.addLocation("L001", "Klang");
        Location loc2 = graph.addLocation("L002", "Bukit Bintang");
        Location loc3 = graph.addLocation("L003", "Bangsar");
        Location loc4 = graph.addLocation("L004", "Cheras");
        Location loc5 = graph.addLocation("L005", "Putrajaya");
        Location loc6 = graph.addLocation("L006", "Mont Kiara");

        graph.addRoad(loc1, loc2, 5);
        graph.addRoad(loc1, loc3, 3);
        graph.addRoad(loc1, loc4, 13);
        graph.addRoad(loc2, loc4, 2);
        graph.addRoad(loc2, loc3, 4);
        graph.addRoad(loc2, loc5, 7);
        graph.addRoad(loc2, loc6, 17);
        graph.addRoad(loc3, loc4, 7);
        graph.addRoad(loc3, loc6, 6);
        graph.addRoad(loc4, loc5, 1);
        graph.addRoad(loc4, loc6, 6);
        graph.addRoad(loc5, loc6, 8);

        dijkstra.findShortestPath(loc1, loc5).display();

        System.out.println("\n=== 5. SEARCH & RECOMMENDATION ===");

        foodTree.insert(new FoodItem("Burger", 4.10));
        foodTree.insert(new FoodItem("Chicken Thighs", 5.00));
        foodTree.insert(new FoodItem("Beef Burger", 5.30));
        foodTree.insert(new FoodItem("Hotdog", 2.50));
        foodTree.insert(new FoodItem("Taco", 3.00));
        foodTree.insert(new FoodItem("Regular Pizza", 12.00));
        
        foodTree.inOrder();

        System.out.println(foodTree.search("Taco"));

        System.out.println("\n=== 6. DATA RETRIEVAL ===");

        System.out.println(dataHashMap.getUser("U001"));
        System.out.println(dataHashMap.getRestaurant("R002"));
        System.out.println(dataHashMap.getOrder("O003"));
    }      
}
