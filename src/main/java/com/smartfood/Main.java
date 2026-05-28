package com.smartfood;

import com.smartfood.users.Restaurant;
import com.smartfood.users.RestaurantLinkedList;
import com.smartfood.users.UserLinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== USER MANAGEMENT =====");
        UserLinkedList users = new UserLinkedList();

        users.addUser("U001", "Alice Tan", "011-1234567");
        users.addUser("U002", "Bob Lim", "012-9876543");
        users.addUser("U003", "Chloe Wong", "013-5556789");

        users.displayUsers();

        users.searchUser("U002");
        users.removeUser("U002");
        users.searchUser("U002"); // should show not found

        users.displayUsers();

        // Restaurants
        System.out.println("===== RESTAURANT MANAGEMENT =====");
        RestaurantLinkedList restaurants = new RestaurantLinkedList();

        restaurants.addRestaurant(new Restaurant("R001", "Nasi Lemak House", "Bangsar"));
        restaurants.addRestaurant(new Restaurant("R002", "Tokyo Ramen", "KLCC"));
        restaurants.addRestaurant(new Restaurant("R003", "Pizza Palace", "Mont Kiara"));

        restaurants.displayRestaurants();

        restaurants.searchRestaurant("R001");
        restaurants.removeRestaurant("R003");
        restaurants.searchRestaurant("R003"); // should show not found

        restaurants.displayRestaurants();
    }
}
