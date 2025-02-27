package Chapter_04;
// linked - Main
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private int balance = 100;

    Lock lock = new ReentrantLock();

    public void withdraw(int amt) {

        try {
            if (lock.tryLock(5500, TimeUnit.MILLISECONDS)){
                System.out.println(Thread.currentThread().getName()+" attempting to withdraw ~"+amt);
                if (balance >= amt){
                    try {
                        System.out.println(Thread.currentThread().getName()+" withdraw processing, please wait...");
                        Thread.sleep(5000); // #Problem with synchronized (readme.md)
                        balance -= amt;
                        System.out.println(Thread.currentThread().getName()+" withdraw success, balance : "+balance);
                    }
                    catch (Exception e){ System.out.println(e); }
                }
                else {
                    System.out.println(Thread.currentThread().getName()+" insufficient balace !!");
                }
            }else {
                System.out.println(Thread.currentThread().getName()+" server busy !! Try again :)");
            }
        }
        catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " " + e);
        }
        finally {
            lock.unlock();
        }

    }

}
