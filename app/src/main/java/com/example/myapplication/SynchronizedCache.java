package com.example.myapplication;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SynchronizedCache {
    private final int MAX_SIZE;  // Maximum size of the cache
    private final Map<Integer, Integer> cache;

    // Constructor with MAX_SIZE parameter
    public SynchronizedCache(int maxSize) {
        this.MAX_SIZE = maxSize;
        // Use LinkedHashMap to maintain insertion order
        cache = Collections.synchronizedMap(new LinkedHashMap<Integer, Integer>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > MAX_SIZE;  // Automatically remove the eldest entry if cache size exceeds MAX_SIZE
            }
        });
    }

    // Add a Integer object to the cache
    public synchronized void put(Integer position, Integer bmp) {
        cache.put(position, bmp);
    }

    // Retrieve a Integer object by position
    public synchronized Integer get(Integer position) {
        return cache.get(position);
    }

    // Remove a Integer object from the cache
    public synchronized Integer remove(Integer position) {
        return cache.remove(position);
    }

    // Check if the cache contains a Integer object by position
    public synchronized boolean contains(Integer position) {
        return cache.containsKey(position);
    }

    // Get the current cache size
    public synchronized int size() {
        return cache.size();
    }
}
