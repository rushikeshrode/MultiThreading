package Chapter_06;
// learn abt CountDownLatch; using Threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Countdownlatch2 {
    public static void main(String[] args) throws InterruptedException {

        int numberOfServices = 3;
        CountDownLatch latch = new CountDownLatch(numberOfServices);

        for (int i = 0; i < numberOfServices; i++) {
            new Thread(new DependentService2(latch)).start();
        }
        latch.await();
        System.out.println("All Count-down-latch-2 threads Done ~~");
    }
}

class DependentService2 implements  Runnable {

    CountDownLatch latch;
    public DependentService2(CountDownLatch latch){ this.latch = latch; }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("Dependent Service 2 is running ....");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            latch.countDown();
        }
    }
}