package com.smartfood.routes;

import java.util.*;

/**
 * Graph data structure representing road network.
 * Uses adjacency list representation for efficiency.
 * 
 * Time Complexity:
 * - Add edge: O(1)
 * - Get neighbors: O(1) average
 * - Space: O(V + E) where V = vertices, E = edges
 */
public class Graph {
    // Adjacency list: maps a location to a list of neighboring locations with distances
    private Map<Location, List<Edge>> adjacencyList;
    
    // Store all locations in the graph
    private Map<String, Location> locations;
    
    /**
     * Inner class representing an edge between two locations
     */
    public static class Edge {
        private Location destination;
        private double weight;  // distance in km or time in minutes
        
        public Edge(Location destination, double weight) {
            this.destination = destination;
            this.weight = weight;
        }
        
        public Location getDestination() {
            return destination;
        }
        
        public double getWeight() {
            return weight;
        }
        
        @Override
        public String toString() {
            return "-> " + destination.getName() + " (" + weight + " km)";
        }
    }
    
    /**
     * Constructor - initializes empty graph
     */
    public Graph() {
        adjacencyList = new HashMap<>();
        locations = new HashMap<>();
    }
    
    /**
     * Add a location (node) to the graph
     * @param id Unique identifier
     * @param name Display name
     * @return The created Location object
     */
    public Location addLocation(String id, String name) {
        if (locations.containsKey(id)) {
            System.out.println("Location with ID " + id + " already exists!");
            return locations.get(id);
        }
        
        Location newLocation = new Location(id, name);
        locations.put(id, newLocation);
        adjacencyList.put(newLocation, new ArrayList<>());
        return newLocation;
    }
    
    /**
     * Get a location by its ID
     * @param id Location identifier
     * @return Location object or null if not found
     */
    public Location getLocation(String id) {
        return locations.get(id);
    }
    
    /**
     * Add a road (edge) between two locations
     * Since roads are bidirectional (two-way), we add edges in both directions
     * 
     * @param from Starting location
     * @param to Destination location
     * @param distance Distance in kilometers (or time in minutes)
     */
    public void addRoad(Location from, Location to, double distance) {
        if (!adjacencyList.containsKey(from)) {
            System.out.println("Location " + from.getName() + " not found in graph!");
            return;
        }
        if (!adjacencyList.containsKey(to)) {
            System.out.println("Location " + to.getName() + " not found in graph!");
            return;
        }
        
        // Add edge in both directions for bidirectional roads
        adjacencyList.get(from).add(new Edge(to, distance));
        adjacencyList.get(to).add(new Edge(from, distance));
        
        System.out.println("Added road: " + from.getName() + " <-- " + distance + "km --> " + to.getName());
    }
    
    /**
     * Get all neighboring locations (adjacent nodes) for a given location
     * @param location The location to get neighbors for
     * @return List of edges to neighboring locations
     */
    public List<Edge> getNeighbors(Location location) {
        return adjacencyList.getOrDefault(location, new ArrayList<>());
    }
    
    /**
     * Get all locations in the graph
     * @return Set of all locations
     */
    public Set<Location> getAllLocations() {
        return adjacencyList.keySet();
    }
    
    /**
     * Display the entire graph structure
     * Useful for visualization and debugging
     */
    public void displayGraph() {
        System.out.println("\n=== ROAD NETWORK GRAPH ===");
        System.out.println("Total Locations: " + adjacencyList.size());
        System.out.println();
        
        for (Map.Entry<Location, List<Edge>> entry : adjacencyList.entrySet()) {
            Location location = entry.getKey();
            List<Edge> edges = entry.getValue();
            
            System.out.print(location.getName() + " [" + location.getId() + "]");
            if (edges.isEmpty()) {
                System.out.println(" -> (No roads connected)");
            } else {
                System.out.println(" is connected to:");
                for (Edge edge : edges) {
                    System.out.println("   └─ " + edge.getDestination().getName() 
                                     + " [" + edge.getDestination().getId() + "]"
                                     + " - " + edge.getWeight() + " km");
                }
            }
            System.out.println();
        }
        System.out.println("========================\n");
    }
}