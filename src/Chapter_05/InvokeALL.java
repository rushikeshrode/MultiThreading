package Chapter_05;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeALL {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        Callable<Integer> task1 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Task 1");
                return 1;
            }
        };
        Callable<Integer> task2 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Task 2");
                return 2;
            }
        };
        Callable<Integer> task3 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Task 3");
                return 3;
            }
        };

        List<Callable<Integer>> list = Arrays.asList(task1, task2, task3);

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.invokeAll(list, 2, TimeUnit.SECONDS);
//        Integer i = executorService.invokeAny(list);
//        System.out.println(i);
        executorService.shutdown();

        System.out.println("Tasks Done ~~~");

    }

}
