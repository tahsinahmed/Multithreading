package com.handson.multithread.parallelstream;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class StreamPerformanceExample1 {
    static long tokenCount = 5000000;
    public static void main(String[] args) {
        int loop = 20;
        long result = measurePerformance(StreamPerformanceExample1::sortSequentialStream, loop);
        System.out.println("Time taken to process sort in sequential: " + result + " in milisecond");
        result = measurePerformance(StreamPerformanceExample1::sortParallelStream, loop);
        System.out.println("Time taken to process sort in parallel : " +result+ " in milisecond");
    }

    public static long measurePerformance(Supplier<Long> supplier, int numberOfTimes) {
        long startTime = System.currentTimeMillis();
        for (int i=0; i<numberOfTimes; i++) {
            supplier.get();
        }
        return System.currentTimeMillis() - startTime;
    }

    public static long sortSequentialStream() {
        List<RandomTokens> randomTokens = LongStream.rangeClosed(0, tokenCount)
                .mapToObj(i -> new RandomTokens(i, ThreadLocalRandom.current().nextLong(tokenCount))).collect(Collectors.toList());
        randomTokens.stream().sorted(Comparator.comparing(RandomTokens::getToken));
        return -1;
    }

    public static long sortParallelStream() {
        List<RandomTokens> randomTokens = LongStream.rangeClosed(0, tokenCount)
                .parallel().mapToObj(i -> new RandomTokens(i, ThreadLocalRandom.current().nextLong(tokenCount))).collect(Collectors.toList());
        randomTokens.stream().parallel().sorted(Comparator.comparing(RandomTokens::getToken));
        return -1;
    }
}

class RandomTokens {
    long id;
    long token;

    public RandomTokens(long id, long token) {
        this.id = id;
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }
}
