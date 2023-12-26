package com.handson.multithread;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class MapToObjectLongDoubelExample {
    public static void main(String[] args) {
        //maToObj
        List<RandomIds> randomIds =
                IntStream.rangeClosed(0, 5)
                        .mapToObj(i -> {
                            return new RandomIds(i, ThreadLocalRandom.current().nextInt(1000));
                        }).collect(Collectors.toList());

        randomIds.forEach(System.out::println);
        System.out.println("------------");

        //mapToLong
        LongStream longStream = IntStream.rangeClosed(0,5).mapToLong(i -> i);
        longStream.forEach(System.out::println);
        System.out.println("-------------");

        //mapToDouble
        DoubleStream doubleStream = LongStream.rangeClosed(0,5)
                .mapToDouble(i -> i);
        doubleStream.forEach(System.out::println);
    }
}

class RandomIds {
    int id;
    int randomNumbers;

    public RandomIds(int id, int randomNumbers) {
        this.id = id;
        this.randomNumbers = randomNumbers;
    }

    @Override
    public String toString() {
        return "RandomIds{" +
                "id=" + id +
                ", randomNumbers=" + randomNumbers +
                '}';
    }
}