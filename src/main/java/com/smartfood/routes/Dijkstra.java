package com.smartfood.routes;

import java.util.*;

/**
 * Dijkstra's Algorithm implementation for finding the shortest path.
 * 
 * Time Complexity: O((V + E) log V) where:
 *   - V = number of vertices (locations)
 *   - E = number of edges (roads)
 * 
 * How Dijkstra's Algorithm Works:
 * 1. Initialize distances to all nodes as INFINITY, source node as 0
 * 2. Use a priority queue to always process the closest unvisited node
 * 3. For current node, check all neighbors
 * 4. If a shorter path to neighbor is found, update distance and add to queue
 * 5. Repeat until all nodes are processed or destination is found
 */
public class Dijkstra {
    
    /**
     * Result class containing the shortest path and its total distance
     */
    public static class ShortestPathResult {
        private List<Location> path;
        private double totalDistance;
        
        public ShortestPathResult(List<Location> path, double totalDistance) {
            this.path = path;
            this.totalDistance = totalDistance;
        }
        
        public List<Location> getPath() {
            return path;
        }
        
        public double getTotalDistance() {
            return totalDistance;
        }
        
        public void display() {
            if (path == null || path.isEmpty()) {
                System.out.println("No path found!");
                return;
            }
            
            System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                    SHORTEST PATH RESULT                      ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            System.out.printf("Total Distance: %.2f km\n", totalDistance);
            System.out.println("\n📍 Route:");
            System.out.print("   ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i).getName());
                if (i < path.size() - 1) {
                    System.out.print(" → ");
                }
            }
            System.out.println("\n");
        }
    }
    
    private Graph graph;
    
    public Dijkstra(Graph graph) {
        this.graph = graph;
    }
    
    /**
     * Find the shortest path from start to destination using Dijkstra's Algorithm
     * 
     * @param start Starting location
     * @param destination Destination location
     * @return ShortestPathResult containing path and total distance
     */
    public ShortestPathResult findShortestPath(Location start, Location destination) {
        // Validation
        if (start == null || destination == null) {
            System.out.println("Error: Start or destination location is null!");
            return new ShortestPathResult(null, Double.POSITIVE_INFINITY);
        }
        
        Set<Location> allLocations = graph.getAllLocations();
        if (!allLocations.contains(start)) {
            System.out.println("Error: Start location " + start.getName() + " not in graph!");
            return new ShortestPathResult(null, Double.POSITIVE_INFINITY);
        }
        if (!allLocations.contains(destination)) {
            System.out.println("Error: Destination location " + destination.getName() + " not in graph!");
            return new ShortestPathResult(null, Double.POSITIVE_INFINITY);
        }
        
        // Distance map: stores the shortest known distance to each location
        Map<Location, Double> distances = new HashMap<>();
        
        // Previous map: stores the previous location in the optimal path
        Map<Location, Location> previous = new HashMap<>();
        
        // Priority queue: stores locations ordered by current shortest distance
        // This gives us O(log V) extraction of the minimum distance node
        PriorityQueue<LocationDistance> priorityQueue = new PriorityQueue<>();
        
        // Step 1: Initialize distances
        for (Location location : allLocations) {
            distances.put(location, Double.POSITIVE_INFINITY);
            previous.put(location, null);
        }
        distances.put(start, 0.0);
        priorityQueue.offer(new LocationDistance(start, 0.0));
        
        // Step 2: Process nodes in order of increasing distance
        while (!priorityQueue.isEmpty()) {
            LocationDistance current = priorityQueue.poll();
            Location currentLocation = current.getLocation();
            double currentDistance = current.getDistance();
            
            // Early exit: If we reached the destination, we can stop
            if (currentLocation.equals(destination)) {
                break;
            }
            
            // Skip if we found a better path already
            if (currentDistance > distances.get(currentLocation)) {
                continue;
            }
            
            // Step 3: Explore neighbors of current node
            List<Graph.Edge> neighbors = graph.getNeighbors(currentLocation);
            for (Graph.Edge edge : neighbors) {
                Location neighbor = edge.getDestination();
                double newDistance = currentDistance + edge.getWeight();
                
                // Step 4: If we found a shorter path to neighbor, update it
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, currentLocation);
                    priorityQueue.offer(new LocationDistance(neighbor, newDistance));
                }
            }
        }
        
        // Step 5: Reconstruct the path from destination back to start
        List<Location> path = reconstructPath(previous, start, destination);
        
        // Step 6: Return result
        if (path.isEmpty()) {
            System.out.println("No path exists from " + start.getName() + " to " + destination.getName());
            return new ShortestPathResult(null, Double.POSITIVE_INFINITY);
        }
        
        return new ShortestPathResult(path, distances.get(destination));
    }
    
    /**
     * Reconstruct the path by following the 'previous' map from destination to start
     * 
     * @param previous Map storing previous node on shortest path
     * @param start Starting location
     * @param destination Destination location
     * @return List of locations in order from start to destination
     */
    private List<Location> reconstructPath(Map<Location, Location> previous, 
                                           Location start, 
                                           Location destination) {
        List<Location> path = new ArrayList<>();
        
        // Check if destination is reachable
        if (previous.get(destination) == null && !destination.equals(start)) {
            return path; // No path exists
        }
        
        // Build path backwards (from destination to start)
        Location current = destination;
        while (current != null) {
            path.add(0, current); // Insert at beginning
            current = previous.get(current);
        }
        
        return path;
    }
    
    /**
     * Helper class for priority queue
     * Implements Comparable so the queue can order by distance
     */
    private static class LocationDistance implements Comparable<LocationDistance> {
        private Location location;
        private double distance;
        
        public LocationDistance(Location location, double distance) {
            this.location = location;
            this.distance = distance;
        }
        
        public Location getLocation() {
            return location;
        }
        
        public double getDistance() {
            return distance;
        }
        
        @Override
        public int compareTo(LocationDistance other) {
            return Double.compare(this.distance, other.distance);
        }
    }
    
    /**
     * Display step-by-step explanation of Dijkstra's Algorithm
     * This is for educational purposes and report writing
     */
    public void explainAlgorithm() {
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║              DIJKSTRA'S ALGORITHM - STEP BY STEP                  ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("HOW DIJKSTRA'S ALGORITHM WORKS:");
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.println();
        System.out.println("Step 1: INITIALIZATION");
        System.out.println("   • Set distance to START node = 0");
        System.out.println("   • Set distance to ALL OTHER nodes = INFINITY");
        System.out.println("   • Mark all nodes as UNVISITED");
        System.out.println("   • Use a PRIORITY QUEUE to always process the closest node");
        System.out.println();
        System.out.println("Step 2: PROCESS CURRENT NODE");
        System.out.println("   • Extract node with SMALLEST distance from priority queue");
        System.out.println("   • This is guaranteed to have its final shortest distance");
        System.out.println();
        System.out.println("Step 3: RELAX NEIGHBORS");
        System.out.println("   • For each neighbor of current node:");
        System.out.println("     • Calculate new distance = current.distance + edge.weight");
        System.out.println("     • IF new distance < neighbor's current distance:");
        System.out.println("         • Update neighbor's distance");
        System.out.println("         • Add neighbor to priority queue");
        System.out.println("         • Record path (set previous[neighbor] = current)");
        System.out.println();
        System.out.println("Step 4: REPEAT");
        System.out.println("   • Continue until priority queue is empty OR destination reached");
        System.out.println();
        System.out.println("Step 5: RECONSTRUCT PATH");
        System.out.println("   • Follow 'previous' pointers from destination back to start");
        System.out.println("   • Reverse the order to get path from start to destination");
        System.out.println();
        System.out.println("TIME COMPLEXITY: O((V + E) log V)");
        System.out.println("   • V = number of vertices (locations)");
        System.out.println("   • E = number of edges (roads)");
        System.out.println("   • log V from priority queue operations");
        System.out.println();
        System.out.println("WHY THIS IS EFFICIENT:");
        System.out.println("   • Better than BFS for weighted graphs");
        System.out.println("   • Better than trying all possible paths (O(V!))");
        System.out.println("   • Priority queue ensures we always process most promising nodes first");
        System.out.println("═══════════════════════════════════════════════════════════════════\n");
    }
}