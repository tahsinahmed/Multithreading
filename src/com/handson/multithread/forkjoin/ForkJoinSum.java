package com.handson.multithread.forkjoin;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ForkJoinSum extends RecursiveAction {
    private static long total = 0;
    private static final int CALCULATE_SUM_THRESHOLD = 5;
    private List<Long> data;

    public ForkJoinSum(List<Long> data) {
        this.data = data;
    }

    public static void main(String[] args) {
        int end = 5000;
        List<Long> data = LongStream.rangeClosed(0, end).boxed().collect(Collectors.toList());
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Pool parallelism " + pool.getParallelism());
        ForkJoinSum task = new ForkJoinSum(data);
        pool.invoke(task);
        System.out.println("Total is: " + total + " and correct sum calculated using stream is: " + LongStream.rangeClosed(0, end).sum());
    }

    @Override
    protected void compute() {
        if (data.size() <= CALCULATE_SUM_THRESHOLD) {
            long sum = computeSumDirectly();
            System.out.println("sum of: " + Arrays.toString(data.toArray()) + " is: " + sum);
        } else {
            int mid = data.size()/2;
            ForkJoinSum firstSubTask = new ForkJoinSum(data.subList(0, mid));
            ForkJoinSum secondTask = new ForkJoinSum(data.subList(mid, data.size()));
            firstSubTask.fork();
            secondTask.compute();
            firstSubTask.join();
        }
    }

    private long computeSumDirectly() {
        long sum = 0;
        for (long l: data) {
            sum+=l;
        }
        total+=sum;
        return sum;
    }
}
