package Chapter_02;
// official naamkaran of Thread

public class ThreadName extends Thread {

    public ThreadName(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "hello");
            }
            catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadName t1 = new ThreadName("RUSH -> ");
        t1.start();

        ThreadName t2 = new ThreadName("SALONI -> ");
        t2.start();

    }

}
