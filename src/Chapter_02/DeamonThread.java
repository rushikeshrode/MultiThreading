package Chapter_02;
// setDaemon(true): if jvm reaches @end & that one thread is keep running, if that one thread is DeamonThread...
// it won't wait for jvm to complete program, it will terminate the thread

public class DeamonThread extends Thread {

    @Override
    public void run() {
        while (true){
            System.out.println("hello...");
        }
    }


    public static void main(String[] args) {

        DeamonThread deamonThread = new DeamonThread();
        deamonThread.setDaemon(true);                       // make sure to setDeamon(true) before start

        deamonThread.start();

        System.out.println("STATUS :: DONE");

    }
}
