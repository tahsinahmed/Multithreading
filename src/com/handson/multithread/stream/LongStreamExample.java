package com.handson.multithread.stream;

import java.util.Random;
import java.util.stream.LongStream;

public class LongStreamExample {
    public static void main(String[] args) {
        //of
        LongStream numbers = LongStream.of(1,2,3,4,5);
        numbers.forEach(System.out::println);

        //iterate
        System.out.println("----------");
        numbers = LongStream.iterate(0, i->i+2).limit(5);
        numbers.forEach(System.out::println);

        //generate
        System.out.println("----------");
        numbers = LongStream.generate(new Random()::nextLong).limit(5);
        numbers.forEach(System.out::println);

        //range
        System.out.println("----------");
        numbers = LongStream.range(1,5);
        numbers.forEach(System.out::println);

        //rangeClosed
        System.out.println("----------");
        numbers = LongStream.rangeClosed(1,5);
        numbers.forEach(System.out::println);
    }
}
