package Chapter_05;
// 1 - used for automation of executing thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class ExecutorFramework {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        ExecutorService executorService = newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            int fi = i;
            executorService.submit(() -> {
                long ans = factorial(fi);
                System.out.println(ans);
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Total time : " + (System.currentTimeMillis() - start));

//        executorService.shutdownNow();
//        System.out.println(executorService.isShutdown());
    }

    static long factorial(int num){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}

        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

}
