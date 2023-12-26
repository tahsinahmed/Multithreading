package com.handson.multithread;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoxingUnboxingExample {
    public static void main(String[] args) {
        List<Integer> numbers;
        IntStream numStream = IntStream.rangeClosed(0, 5000);
        numbers = numStream.boxed().collect(Collectors.toList());
        numbers.forEach(System.out::println);

        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        sum.ifPresent(System.out::println);

        int sum1 = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum1);
    }
}
