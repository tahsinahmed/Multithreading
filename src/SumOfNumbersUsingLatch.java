import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SumOfNumbersUsingLatch {
    public static int[] array = IntStream.rangeClosed(0, 5000).toArray();
    public final static int total = IntStream.rangeClosed(0, 5000).sum();
    final static CountDownLatch countdownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> callable = () -> {
            int sum = 0;
            for (int i=0; i<array.length/2; i++)
                sum+=array[i];
            countdownLatch.countDown();
            return sum;
        };

        Callable<Integer > callable1 = () ->{
            int sum = 0;
            for (int i=array.length/2; i<array.length; i++)
                sum+=array[i];
            countdownLatch.countDown();
            return sum;
        };


        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> sum1 = service.submit(callable1);
        Future<Integer> sum2 = service.submit(callable);
        System.out.println("Count Down Latch count before calling the awit: " + countdownLatch.getCount());
        countdownLatch.await();
        System.out.println("Count Down Latch count after await: " + countdownLatch.getCount());
        int sum = sum1.get() + sum2.get();

        System.out.println("Sum from the thread is: " + sum);
        System.out.println("Total actual value: "+ total);
        service.shutdown();
    }
}
