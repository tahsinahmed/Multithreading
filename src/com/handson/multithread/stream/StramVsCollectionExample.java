package com.handson.multithread.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StramVsCollectionExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Mike");
        names.add("Syed");
        names.add("Rajeev");
        System.out.println("----------");
        System.out.println(names);
        names.remove("Syed");
        System.out.println("----------");
        System.out.println(names);

        for (String name: names)
            System.out.println(name );

        Stream<String> nameStream = names.stream();
        nameStream.forEach(System.out::println);
//        nameStream.forEach(System.out::println); cannot be used the same stream after completing terminal operation
    }
}
