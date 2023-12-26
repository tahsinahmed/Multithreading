package com.handson.multithread;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ConcurrentTokenGeneration {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        new Thread(() -> {
            Random randomNumber = new Random();
            for (int i=0; i<10000; i++) {
                concurrentHashMap.put(i, randomNumber.nextInt(10000));
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            Random randomNumber = new Random();
            for (int i=10000; i<20000; i++) {
                concurrentHashMap.put(i, randomNumber.nextInt(20000));
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Random randomNumber = new Random();
                int key = randomNumber.nextInt(20000);
                Integer value = concurrentHashMap.get(key);
                if (value!= null) {
                    System.out.println("Key: " + key + " value: " + value);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
