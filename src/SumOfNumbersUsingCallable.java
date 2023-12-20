import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SumOfNumbersUsingCallable {
    public static int[] array = IntStream.rangeClosed(0, 5000).toArray();
     static int total = IntStream.rangeClosed(0, 5000).sum();

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Callable<Integer> callable = () ->{
            int sum = 0;
            for (int i=0; i< array.length/2; i++)
                sum+=array[i];
            return sum;
        };

        Callable<Integer> callable1 = () ->{
            int sum = 0;
            for (int i=array.length/2; i<array.length; i++)
                sum+=array[i];
            return sum;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> taskList = Arrays.asList(callable,callable1);
        List<Future<Integer>> results = executorService.invokeAll(taskList);
        List<Integer> it = new ArrayList<>();

        int k=0, sum=0;

        for (Future<Integer> result: results) {
            sum+=result.get();
            System.out.println("SUm "+ ++k + " is: " + result.get());
        }

        System.out.println("SUm from the thread is: " + sum);
        System.out.println("Correct sum from Intstream,sum is: " + total);
        executorService.shutdown();
    }
}
