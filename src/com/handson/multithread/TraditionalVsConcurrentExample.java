package com.handson.multithread;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class TraditionalVsConcurrentExample {
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        concurrentModificationArrayList();
    }

    public static void concurrentModificationArrayList() throws BrokenBarrierException, InterruptedException {
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<100000; i++) {
            list.add(i);
        }
        new Thread(() -> {
            try {
                System.out.println("Adding 50000 to the list");
                cyclicBarrier.await();
                System.out.println("Await called");
                list.add(5000);
            } catch (ConcurrentModificationException exception) {
                exception.printStackTrace();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();

        Iterator<Integer> it = list.iterator();
        boolean flag = false;
        while (it.hasNext()) {
            it.next();
            if (!flag) {
                System.out.println("Iterator called");
                cyclicBarrier.await();
                System.out.println("Await called twice");
                flag = true;
            }
        }
    }
}
