package com.handson.multithread.stream;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        //creating a map of names and courses of instructors who teaches
        // online have more than 10 years of experience

        Predicate<Instructor> p1 = Instructor::isOnlineCourses;
        Predicate<Instructor> p2 = i -> i.getYearsOfExperience() > 10;
        List<Instructor> list = Instructors.getAll();
        Map<String, List<String>> map = list.stream()
                .filter(p1)
                .filter(p2)
                .collect(Collectors.toMap(Instructor::getName, Instructor::getProgrammingLanguages));
        System.out.println(map);
    }
}
