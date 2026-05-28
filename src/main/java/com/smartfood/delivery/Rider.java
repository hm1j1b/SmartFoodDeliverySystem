package com.smartfood.delivery;

// Represents a delivery rider with a priority value (distance in km)
public class Rider {
    private String riderId;
    private String name;
    private double distanceKm; // Priority key: lower = higher priority in Min Heap

    // Constructor
    public Rider(String riderId, String name, double distanceKm) {
        this.riderId = riderId;
        this.name = name;
        this.distanceKm = distanceKm;
    }

    // Getters and Setters
    public String getRiderId() {
        return riderId;
    }
    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getDistanceKm() {
        return distanceKm;
    }
    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    // Display rider details
    @Override
    public String toString() {
        return "Rider ID: " + riderId + " | Name: " + name + " | Distance: " + distanceKm + " km";
    }
}