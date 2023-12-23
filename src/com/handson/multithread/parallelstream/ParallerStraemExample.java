package com.handson.multithread.parallelstream;

import java.util.stream.IntStream;

public class ParallerStraemExample {
    public static void main(String[] args) {
        System.out.println("Sum of sequential: " + sumOfSequentialStream());
        System.out.println("Sum of parallel: " + sumParallelStream());
    }

    public static int sumOfSequentialStream() {
        return IntStream.rangeClosed(0, 50000).sum();
    }

    public static int sumParallelStream() {
        return IntStream.rangeClosed(0, 50000).parallel().sum();
    }
}
