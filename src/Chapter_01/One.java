package Chapter_01;
// to create a thread in Java you can
// 1- either extend a class[Thread] or

public class One extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("        One -> "+ Thread.currentThread().getName());
        }
    }

}
