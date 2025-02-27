package Chapter_06;
// learn abt CountDownLatch; using ExecutorService;
import java.util.concurrent.*;

public class Countdownlatch {
    public static void main(String[] args) throws InterruptedException {

        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CountDownLatch latch = new CountDownLatch(numberOfServices);

        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));

        latch.await();
        executorService.shutdown(); // use shutdownNow() if u want immediate exit after latch thread execution
        System.out.println("All threads Done ~~");

    }
}

class DependentService implements Callable<String> {

    CountDownLatch latch;
    public DependentService(CountDownLatch latch){ this.latch = latch; }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " :: DependentService is running ...");
        }
        finally {
            latch.countDown();
        }
        return "ok !";
    }
}