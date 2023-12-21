package com.handson.multithread.dinningphilosopherproblem;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Dinning philosopher problem: The problem is that each philosopher needs 2 forks to eat, and there are only N forks, one
 * between each 2 philosophers. Design an algorithm that the philosophers can follow that
 * ensures that none starves as long as each philosopher eventually stops eating, and such that the
 * maximum number of philosophers can eat at once.
 */
public class DinningPhilosophers {
    static Lock fork1 = new ReentrantLock();
    static Lock fork2 = new ReentrantLock();
    static Lock fork3 = new ReentrantLock();
    static Lock fork4 = new ReentrantLock();
    static Lock fork5 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Pholosopher 1 is thinking");
            fork2.lock();
            System.out.println("Philosopher 1 picked up left fork");
            fork1.lock();
            System.out.println("Philosopher 1 is now eating");
            fork1.unlock();
            fork2.unlock();
        }).start();

        new Thread(() -> {
            System.out.println("Pholosopher 2 is thinking");
            fork3.lock();
            System.out.println("Philosopher 2 picked up left fork");
            fork2.lock();
            System.out.println("Philosopher 2 is now eating");
            fork2.unlock();
            fork3.unlock();
        }).start();

        new Thread(() -> {
            System.out.println("Pholosopher 3 is thinking");
            fork4.lock();
            System.out.println("Philosopher 3 picked up left fork");
            fork3.lock();
            System.out.println("Philosopher 3 is now eating");
            fork3.unlock();
            fork4.unlock();
        }).start();

        new Thread(() -> {
            System.out.println("Pholosopher 4 is thinking");
            fork5.lock();
            System.out.println("Philosopher 4 picked up left fork");
            fork4.lock();
            System.out.println("Philosopher 4 is now eating");
            fork4.unlock();
            fork5.unlock();
        }).start();

        new Thread(() -> {
            System.out.println("Pholosopher 5 is thinking");
            fork1.lock();
            System.out.println("Philosopher 5 picked up left fork");
            fork5.lock();
            System.out.println("Philosopher 5 is now eating");
            fork5.unlock();
            fork1.unlock();
        }).start();


    }
}
