package Chapter_01;
// to create a thread in Java you can
// 2- you can implement interface[Runnable]

public class Two implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("    Two -> "+ Thread.currentThread().getName());
        }
    }

}
