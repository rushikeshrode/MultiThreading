package Chapter_02;
// set a priority between (low, medium, high), not accuratly works like high->medium->low

public class ThreadPriority extends Thread {

    public ThreadPriority(String name){super(name);}

    @Override
    public void run(){
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getPriority() + " :: " + Thread.currentThread().getName());
                Thread.sleep(500);
                Thread.yield();             // says: you can execute another thread in between
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        ThreadPriority t1 = new ThreadPriority("HIGH");
        ThreadPriority t2 = new ThreadPriority("MID");
        ThreadPriority t3 = new ThreadPriority("LOW");

        t1.start();
        t2.start();
        t3.start();

        t1.setPriority(MAX_PRIORITY);
        t3.setPriority(MIN_PRIORITY);

//        t3.interrupt();     // stops the execution of thread

    }


}
