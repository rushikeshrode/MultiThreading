package Chapter_04;
// linked - Stand-Alone
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockThread {

    public int counter = 0;

    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock writeLock = lock.writeLock();
    Lock readLock = lock.readLock();

    public void increment(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
            counter++;
        }finally {
            writeLock.unlock();
        }
    }

    public int getCounter(){
        readLock.lock();
        try {
            System.out.print(Thread.currentThread().getName() + " : ");
            return counter;
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ReadWriteLockThread task = new ReadWriteLockThread();

        Runnable read = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(task.getCounter());
                }
            }
        };

        Runnable write = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    task.increment();
                    System.out.println(Thread.currentThread().getName() + " : " + task.counter);
                }
            }
        };

        Thread t1 = new Thread(write, "writer[1]");
        Thread t2 = new Thread(read, "reader[1]");
        Thread t3 = new Thread(read, "reader[2]");

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println("~~ ALL Done ~~");

    }

}
