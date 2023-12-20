package com.handson.multithread;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class ExecutorRunnable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " Finished executing at: "+ LocalDateTime.now());
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println("First Example - executing task with execute() method");
        executorService.execute(runnable);
        System.out.println("Second Example - executing task with submit() method");
        Future<String> result = executorService.submit(runnable, "COMPLETED");

        while (!result.isDone()) {
            System.out.println("The method return value: " + result.get());

        executorService.shutdown();        }
    }
}
