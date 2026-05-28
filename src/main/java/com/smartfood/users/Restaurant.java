package com.smartfood.users;

public class Restaurant {

    private String name;
    private String location;
    private String id;

    // Constructor
    public Restaurant(String id, String name, String location) {
        this.name = name;
        this.location = location;
        this.id = id;
    }

    // Setters and Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Restaruant ID: " + id + " | Name: " + name + " | Location: " + location;
    }
}
