import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore Example
 */
public class BoundCollection {
    private final Semaphore semaphore;
    private final List<Integer> arrayList;

    public BoundCollection(int limit) {
        semaphore = new Semaphore(limit);
        this.arrayList = Collections.synchronizedList(new ArrayList<>());
    }

    public boolean add(Integer i) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.submit(() -> System.out.println("HEllo WOrld"));
        boolean added = false;
        semaphore.acquire();
        added = arrayList.add(i);
        if (!added) {
            semaphore.release();
        }
        return added;

    }

    public boolean remove(Integer i) {
        boolean removed = arrayList.remove(i);
        if (removed) {
            semaphore.release();
        }
        return removed;
    }

    public static void main(String[] args) {
        final BoundCollection boundCollection = new BoundCollection(10);
        new Thread(() -> {
            for (int i=0; i<20; i++) {
                try {
                    boundCollection.add(i);
                    System.out.println(Thread.currentThread().getName() + " added value: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i=0; i<20; i++) {
                if (boundCollection.remove(i)) {
                    System.out.println(Thread.currentThread().getName() + " removing value: " + i);
                }
            }
        }).start();
    }
}
