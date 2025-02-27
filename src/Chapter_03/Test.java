package Chapter_03;
// the counter should be 1000+1000=2000, but it wont bcz of race condition
// use synchronized keyword, or synchronized block
public class Test {

    public static void main(String[] args) {

        Counter counter = new Counter();
        MyThread t1 = new MyThread(counter);
        MyThread t2 = new MyThread(counter);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e) {}

        System.out.println(counter.getCount());

    }
}
