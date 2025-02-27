package Chapter_06;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierRR {
    public static void main(String[] args) {

        int services = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(services);
        CyclicBarrier barrier = new CyclicBarrier(services);

        executorService.submit(new MyThread(barrier));
        executorService.submit(new MyThread(barrier));
        executorService.submit(new MyThread(barrier));
        executorService.shutdown();
        System.out.println("All Cyclic Barrier threads Done ~~");

    }

}

class MyThread implements Runnable {

    CyclicBarrier barrier;

    public MyThread(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started ....");
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " Finished !!");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println(e);
            }
        }
    }
}