package Chapter_01;
// to create a thread in Java you can
// 1- either extend a class[Thread] or
// 2- you can implement interface[Runnable]

public class Main {
    public static void main(String[] args) {

        // execution using class Thread
        One one = new One();
        one.start();              // start() method is from Thread

        for (int i = 0; i < 100; i++) {
            System.out.println("Main(1) -> "+ Thread.currentThread().getName());
        }


        // execution using interface Runnable
        Two two = new Two();
        Thread t1 = new Thread(two);
        t1.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("Main(2) -> "+ Thread.currentThread().getName());
        }

    }
}
