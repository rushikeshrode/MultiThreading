package Chapter_04;
// linked : Stand-Alone

class SharedRes {

    int data;
    boolean hasData;

    public synchronized void produce(int val){
        while (hasData){  // Wait while there is already data
            try {
                wait();
            }
            catch (Exception e){
                Thread.currentThread().interrupt();
            }
        }
        data = val;
        hasData = true;
        System.out.println("Produced : " + data);
        notify();
    }

    public synchronized void consume(){
        while (!hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        System.out.println("Consumed : " + data);
        notify();
    }
}

class Produce implements Runnable {

    SharedRes res;
    public Produce(SharedRes resources){this.res = resources;}
    @Override
    public void run() {
        for (int i = 1; i < 11; i++) {
            res.produce(i);
        }
    }
}

class Consume implements Runnable {

    SharedRes res;
    public Consume(SharedRes resources){this.res = resources;}
    @Override
    public void run() {
        for (int i = 1; i < 11; i++) {
            res.consume();
        }
    }
}

//---------------------------------------------------------------------------------------------------------//

public class ThreadCommunication {
    public static void main(String[] args) {

        SharedRes sharedRes = new SharedRes();
        Thread producerThread = new Thread(new Produce(sharedRes));
        Thread consumerThread = new Thread(new Consume(sharedRes));

        producerThread.start();
        consumerThread.start();

    }
}
