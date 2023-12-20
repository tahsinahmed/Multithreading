import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> {
            TimeUnit.MILLISECONDS.sleep(1000);
            return "Current Time is " + LocalDateTime.now();
        };
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        List<Callable<String>> taskList = Arrays.asList(callable, callable);

        System.out.println("First example using invokeAll()");
        List<Future<String>> results = executorService.invokeAll(taskList);

        for (Future<String> result: results) {
            System.out.println(result.get());
        }

        System.out.println("Executing callable using submit");
        Future<String> result = executorService.submit(callable);

        while (!result.isDone()) {
            System.out.println("The method return value: " + result.get());
        }
        executorService.shutdown();
    }
}
