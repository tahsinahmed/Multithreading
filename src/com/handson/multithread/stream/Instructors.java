package com.handson.multithread.stream;

import java.util.Arrays;
import java.util.List;

public class Instructors {
    public static List<Instructor> getAll() {
        Instructor instructor1 = new Instructor("Mike",11, "Software Engineer", "M", true, Arrays.asList("Java Programming", "C++ Programming"));
        Instructor instructor2 = new Instructor("Jenny",5, "Engineer", "F", false, Arrays.asList("Unit Test", "CI/CD", "Multi Threaded Programming"));
        return Arrays.asList(instructor1, instructor2);
    }
}
