package Chapter_06;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureRR {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync :: started...");
            try {
                Thread.sleep(1500);
                System.out.println("finished [supplyAsync] !!");
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "OKK";
        }).get();               // method chaining, many methods available

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync :: started...");
            try {
                Thread.sleep(1500);
                System.out.println("finished [runAsync] !!");
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        future.join();
        System.out.println("hello world");

    }
}
