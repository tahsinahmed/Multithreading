package com.handson.multithread.parallelstream;

import java.util.function.Supplier;
import java.util.stream.IntStream;

public class StreamPerformanceExample {
    public static void main(String[] args) {
        int loop = 2;
        long result = measurePerformance(StreamPerformanceExample::sumOfSequentialStream, loop);
        System.out.println("Sum of time taken to process sum in sequential: " + result + " in milisecond");
        result = measurePerformance(StreamPerformanceExample::sumOfParallel, loop);
        System.out.println("Time taken to process sum of parallel: " + result + " in milisecond");
    }

    public static long measurePerformance(Supplier<Integer> supplier, int numOfTimes)  {
        long startTime = System.currentTimeMillis();
        for (int i=0; i<numOfTimes; i++) {
            supplier.get();
        }
        return System.currentTimeMillis() - startTime;
    }

    public static int sumOfSequentialStream() {
        return IntStream.rangeClosed(0, 500000000).sum();
    }

    public static int sumOfParallel() {
        return IntStream.rangeClosed(0, 500000000).parallel().sum();
    }
}
