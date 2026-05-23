package com.smartfood.users;

public class User {
    // Thank you syahmi for the generate constructors tip
    private String id;
    private String name;
    private String contactInfo;
    
    // Constructor
    public User(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Setters and Getters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // To display user details
    @Override
    public String toString(){
        return "User ID: " + id + " | Name: " + name + " | Contact: " + contactInfo;
    }
}
