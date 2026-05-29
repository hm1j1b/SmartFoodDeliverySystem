package com.smartfood.routes;

/**
 * Represents a location/node in the graph.
 * Each location has a unique ID and a name.
 */
public class Location {
    private String id;      // Unique identifier for the location (e.g., "R1", "C1")
    private String name;    // Display name (e.g., "McDonald's", "Customer A")
    
    /**
     * Constructor to create a new location
     * @param id Unique identifier
     * @param name Display name of the location
     */
    public Location(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return id.equals(location.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}