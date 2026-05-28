package com.smartfood.delivery;

// Manual Min Heap implementation — stores Riders ordered by distanceKm (smallest = top)
// This is the core data structure for efficient delivery assignment (O(log n) operations)
public class DeliveryMinHeap {
    private Rider[] heap;
    private int size;
    private int capacity;

    // Constructor
    public DeliveryMinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new Rider[capacity];
    }

    // --- Index helpers ---
    private int parentIndex(int i)    { return (i - 1) / 2; }
    private int leftChildIndex(int i) { return 2 * i + 1; }
    private int rightChildIndex(int i){ return 2 * i + 2; }

    private boolean hasParent(int i)     { return parentIndex(i) >= 0; }
    private boolean hasLeftChild(int i)  { return leftChildIndex(i) < size; }
    private boolean hasRightChild(int i) { return rightChildIndex(i) < size; }

    private Rider parent(int i)     { return heap[parentIndex(i)]; }
    private Rider leftChild(int i)  { return heap[leftChildIndex(i)]; }
    private Rider rightChild(int i) { return heap[rightChildIndex(i)]; }

    private void swap(int i, int j) {
        Rider temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // --- Core Operations ---

    // Insert a rider — O(log n)
    // Adds to end of array, then bubbles UP to restore heap property
    public void addRider(Rider rider) {
        if (size >= capacity) {
            System.out.println("[Heap] Capacity full. Cannot add rider: " + rider.getName());
            return;
        }
        heap[size] = rider;
        size++;
        heapifyUp(size - 1);
        System.out.println("[Heap] Rider added: " + rider.getName() + " (" + rider.getDistanceKm() + " km)");
    }

    // Remove and return the nearest rider (top of heap) — O(log n)
    // Swaps root with last element, reduces size, then bubbles DOWN
    public Rider assignNearestRider() {
        if (size == 0) return null;

        Rider nearest = heap[0];          // Min is always at root
        heap[0] = heap[size - 1];         // Move last element to root
        heap[size - 1] = null;
        size--;
        heapifyDown(0);                   // Restore heap property downward

        return nearest;
    }

    // Peek at the nearest rider WITHOUT removing — O(1)
    public Rider peekNearestRider() {
        if (size == 0) return null;
        return heap[0];
    }

    // Bubble UP: after insert, move new element up until heap property is satisfied
    private void heapifyUp(int i) {
        while (hasParent(i) && parent(i).getDistanceKm() > heap[i].getDistanceKm()) {
            swap(parentIndex(i), i);
            i = parentIndex(i);
        }
    }

    // Bubble DOWN: after removal, move root down until heap property is satisfied
    private void heapifyDown(int i) {
        while (hasLeftChild(i)) {
            int smallerChildIndex = leftChildIndex(i);

            // Pick the smaller of the two children
            if (hasRightChild(i) && rightChild(i).getDistanceKm() < leftChild(i).getDistanceKm()) {
                smallerChildIndex = rightChildIndex(i);
            }

            // If current node is already smaller than its smallest child, stop
            if (heap[i].getDistanceKm() <= heap[smallerChildIndex].getDistanceKm()) {
                break;
            }

            swap(i, smallerChildIndex);
            i = smallerChildIndex;
        }
    }

    // --- Linear Search (for performance comparison) ---
    // Finds nearest rider by scanning the entire array — O(n)
    public Rider linearSearchNearestRider() {
        if (size == 0) return null;
        Rider nearest = heap[0];
        for (int i = 1; i < size; i++) {
            if (heap[i].getDistanceKm() < nearest.getDistanceKm()) {
                nearest = heap[i];
            }
        }
        return nearest;
    }

    // Display current heap state
    public void displayHeap() {
        if (size == 0) {
            System.out.println("[Heap] No riders currently available.");
            return;
        }
        System.out.println("[Heap] Available Riders (heap order):");
        for (int i = 0; i < size; i++) {
            System.out.println("  [" + i + "] " + heap[i]);
        }
    }

    public boolean isEmpty() { return size == 0; }
    public int getSize()     { return size; }
}