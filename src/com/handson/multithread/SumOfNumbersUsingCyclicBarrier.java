package com.handson.multithread;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SumOfNumbersUsingCyclicBarrier {
    private static int[] array1 = IntStream.rangeClosed(0, 5000).toArray();
    private static int[] array2 = IntStream.rangeClosed(5001, 10000).toArray();
    private static int[] array3 = IntStream.rangeClosed(10001, 15000).toArray();

    private static int total = IntStream.rangeClosed(0, 15000).sum();
    final static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public static void main(String[] args) throws InterruptedException, ExecutionException, BrokenBarrierException {
        Callable<Integer> callable = () ->{
            int sum = 0;
            sum+=calculateSum(0, array1.length/2, array1);
            cyclicBarrier.await();
            sum+=calculateSum(0, array2.length/2, array2);
            cyclicBarrier.await();
            sum+=calculateSum(0, array3.length/2, array3);
            cyclicBarrier.await();
            return sum;
        };

        Callable<Integer> callable1 = () ->{
            int sum = 0;
            sum+=calculateSum(array1.length/2, array1.length, array1);
            cyclicBarrier.await();
            sum+=calculateSum(array2.length/2, array2.length, array2);
            cyclicBarrier.await();
            sum+=calculateSum(array3.length/2, array3.length, array3);
            cyclicBarrier.await();
            return sum;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> sum1 = executorService.submit(callable);
        Future<Integer> sum2 = executorService.submit(callable1);

        System.out.println("Calculating first sum ");
        cyclicBarrier.await();
        System.out.println("First sum is calculated");
        System.out.println("calculating second sum ");
        cyclicBarrier.await();
        System.out.println("Second sum is calculated ");
        System.out.println("Calculating third sum");
//        cyclicBarrier.await();
        System.out.println("Third sum is calculated");

        int sum = sum1.get() + sum2.get();

        System.out.println("SUm of three arrays: "+ sum);

        System.out.println("Correct sum: " + total);
        executorService.shutdown();
    }

    private static int calculateSum(int start, int end, int[] array1) {
        int sum1 = 0;
        for (int i=start; i<end; i++)
            sum1+=array1[i];
        return sum1;
    }
}
