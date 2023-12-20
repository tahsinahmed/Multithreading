package com.handson.multithread;

import java.util.concurrent.*;

public class LinkedBlockingQueueExample {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>(5);
        Runnable producer = () -> {
            int i=0;
            while (true) {
                try {
                    linkedBlockingQueue.put(++i);
                    System.out.println("Added " + i);
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable consumer = () ->{
            while (true) {
                Integer poll;
                try {
                    TimeUnit.MILLISECONDS.sleep(10000);
                    poll = linkedBlockingQueue.take();
                    System.out.println("Polled " + poll);
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(producer);
        executorService.submit(consumer);

        executorService.shutdown();
    }
}
