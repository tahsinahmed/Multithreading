package com.handson.multithread;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class NavigableMapExample {
    public static void main(String[] args) {
        ConcurrentNavigableMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");
        map.put(6, "F");
        map.put(7, "G");
        map.put(8, "");
        map.put(9, "I");
        map.put(10, "j");

        System.out.println("Navigable Map "+ map);
        System.out.println("HashMap (return all < key: " + map.headMap(3));
        System.out.println("HashMap (return all > key: " + map.tailMap(3));
        System.out.println("Submap (return all between excluding the last: " + map.subMap(2,4));

    }
}
