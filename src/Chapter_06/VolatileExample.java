package Chapter_06;
// Not for complex operations (like x++) – Use synchronized or AtomicInteger instead.
class ExampleOne extends Thread {

    volatile boolean running = true;        // # Without `volatile`, thread may not stop due to caching

    @Override
    public void run() {
        while (running) {
            System.out.print("~");
            // do nothing, stay in loop
        }
        System.out.println("Thread stopped !! ");
    }
    public void stopRunning() {     running = false;    }

}

public class VolatileExample {
    public static void main(String[] args) throws InterruptedException {

        ExampleOne one = new ExampleOne();
        one.start();

        Thread.sleep(1000);
        one.stopRunning();      // Without `volatile`, thread may not stop due to caching

    }
}
