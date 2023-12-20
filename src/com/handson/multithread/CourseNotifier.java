package com.handson.multithread;

public class CourseNotifier {
    public static void main(String[] args) {
        final Course course = new Course("Java Multithreading programming");

        Thread t1 = new Thread(() -> {
            synchronized (course) {
                System.out.println(Thread.currentThread().getName() + " is waiting for the course: " + course.getTitle());
                try {
                    course.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " the course: " + course.getTitle());
            }
        }, "StudentA");

        Thread t2 = new Thread(() -> {
            synchronized (course) {
                System.out.println(Thread.currentThread().getName() + " is starting new course: " + course.getTitle());
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                course.notifyAll();

            }
        }, "Instructor");


        Thread t3 = new Thread(() -> {
            synchronized (course) {
                System.out.println(Thread.currentThread().getName() + " is waiting for the course: " + course.getTitle());
                try {
                    course.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " the course: " + course.getTitle());
            }
        }, "StudentB");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Course {
    private String title;
    private boolean completed;

    public Course(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
