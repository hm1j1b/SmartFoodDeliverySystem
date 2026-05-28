package com.smartfood.delivery;

// Core system that manages rider registration and delivery assignment
// Uses a Min Heap (Priority Queue) to always assign the nearest available rider
public class DeliverySystem {
    private DeliveryMinHeap riderHeap;

    // Constructor
    public DeliverySystem(int capacity) {
        this.riderHeap = new DeliveryMinHeap(capacity);
    }

    // Register a new rider into the system
    public void registerRider(String id, String name, double distanceKm) {
        System.out.println("\n>> Registering Rider: " + name);
        Rider rider = new Rider(id, name, distanceKm);
        riderHeap.addRider(rider);
    }

    // Assign the nearest rider to an incoming order
    public void assignRiderToOrder(String orderId) {
        System.out.println("\n>> Assigning rider for Order: " + orderId);
        if (riderHeap.isEmpty()) {
            System.out.println("[Delivery] No riders available at the moment.");
            return;
        }
        Rider assigned = riderHeap.assignNearestRider();
        System.out.println("[Delivery] Nearest rider assigned -> " + assigned);
        System.out.println("[Delivery] Rider " + assigned.getName() + " is now heading to deliver " + orderId + "!");
    }

    // Show who would be assigned next without committing
    public void previewNextRider() {
        Rider next = riderHeap.peekNearestRider();
        if (next == null) {
            System.out.println("[Preview] No riders in queue.");
        } else {
            System.out.println("[Preview] Next rider to be assigned: " + next);
        }
    }

    // Show all currently available riders
    public void showAvailableRiders() {
        System.out.println("\n>> Current Rider Pool:");
        riderHeap.displayHeap();
    }

    // --- Performance Comparison Demo ---
    // Demonstrates why Min Heap beats linear search for rider assignment
    private static void performanceComparison() {
        System.out.println("\n============================================================");
        System.out.println("   PERFORMANCE COMPARISON: Min Heap vs Linear Search");
        System.out.println("============================================================");

        DeliveryMinHeap testHeap = new DeliveryMinHeap(10);
        testHeap.addRider(new Rider("R010", "Ali",     8.5));
        testHeap.addRider(new Rider("R011", "Budi",    2.1));
        testHeap.addRider(new Rider("R012", "Chong",   5.7));
        testHeap.addRider(new Rider("R013", "Devi",    1.3));
        testHeap.addRider(new Rider("R014", "Encik Hafiz", 9.0));

        System.out.println("\n[Linear Search] Scanning all " + testHeap.getSize() + " riders to find nearest...");
        long startLinear = System.nanoTime();
        Rider linearResult = testHeap.linearSearchNearestRider();
        long endLinear = System.nanoTime();
        System.out.println("[Linear Search] Found: " + linearResult.getName() + " (" + linearResult.getDistanceKm() + " km)");
        System.out.println("[Linear Search] Time taken: " + (endLinear - startLinear) + " ns | Complexity: O(n)");

        System.out.println();

        System.out.println("[Min Heap] Reading nearest rider from root...");
        long startHeap = System.nanoTime();
        Rider heapResult = testHeap.peekNearestRider();
        long endHeap = System.nanoTime();
        System.out.println("[Min Heap]     Found: " + heapResult.getName() + " (" + heapResult.getDistanceKm() + " km)");
        System.out.println("[Min Heap]     Time taken: " + (endHeap - startHeap) + " ns | Complexity: O(1) peek / O(log n) insert+remove");

        System.out.println("\n[Summary]");
        System.out.println("  Linear Search : Must scan every rider -> O(n). Slower as rider pool grows.");
        System.out.println("  Min Heap      : Nearest rider always at root -> O(1) peek, O(log n) assign.");
        System.out.println("  With 1000 riders, linear search does 1000 comparisons.");
        System.out.println("  Min Heap only needs ~10 comparisons (log2(1000) ≈ 10). Far more efficient!");
        System.out.println("============================================================\n");
    }

    // Simulation Runner
    public static void main(String[] args) {
        DeliverySystem system = new DeliverySystem(10);

        System.out.println("=== SYSTEM DEMO: REGISTERING AVAILABLE RIDERS ===");
        system.registerRider("R001", "Ahmad",   4.5);
        system.registerRider("R002", "Siti",    1.8);
        system.registerRider("R003", "Kumar",   7.2);
        system.registerRider("R004", "Wei Ling",3.0);
        system.registerRider("R005", "Fariz",   0.9);

        system.showAvailableRiders();

        System.out.println("\n=== SYSTEM DEMO: PREVIEW NEXT ASSIGNMENT ===");
        system.previewNextRider();

        System.out.println("\n=== SYSTEM DEMO: ASSIGNING RIDERS TO ORDERS ===");
        system.assignRiderToOrder("ORD001"); // Should assign Fariz (0.9 km)
        system.assignRiderToOrder("ORD002"); // Should assign Siti  (1.8 km)
        system.assignRiderToOrder("ORD003"); // Should assign Wei Ling (3.0 km)

        system.showAvailableRiders();

        // Performance comparison section
        performanceComparison();
    }
}
