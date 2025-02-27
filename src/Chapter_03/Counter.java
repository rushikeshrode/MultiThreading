package Chapter_03;
// if we remove synchronized keyword, it will again run into race condition

public class Counter {

    private int count = 0;

    public int getCount(){
        return count;
    }

    public synchronized void increment(){
        count++;
    }

}
/*

1)      Keyword synchronized
    public synchronized void increment(){
        count++;
    }
2)      synchronized block
    public synchronized void increment(){
        synchronized (this){
            count++;
        }
        count++;
    }

 */