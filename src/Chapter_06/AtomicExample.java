package Chapter_06;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                atomicInteger.incrementAndGet();
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                atomicInteger.incrementAndGet();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(atomicInteger);
    }
}
