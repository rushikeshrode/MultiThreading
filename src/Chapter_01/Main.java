package Chapter_01;
// to create a thread in Java you can
// 1- either extend a class[Thread] or 2- you can implement interface[Runnable]
// you can chack the state of Thread using [.getState()] method

public class Main {
    public static void main(String[] args) throws InterruptedException {

        One one = new One();            // execution using class Thread
        one.start();                    // start() method is from Thread
        one.join();                     // waits for it to completely finished
        System.out.println("STATUS : "+ one.getState());

        for (int i = 0; i < 100; i++) {

            System.out.println("Main(1) -> "+ Thread.currentThread().getName());
        }

//------------------------------------------------------------------------------------------

        Two two = new Two();            // execution using interface Runnable
        Thread t1 = new Thread(two);
        t1.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("Main(2) -> "+ Thread.currentThread().getName());
        }
        System.out.println(t1.getState());

    }
}
