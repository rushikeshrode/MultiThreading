package Chapter_04;
// linked - BankAccount
public class Main {
    public static void main(String[] args) {

        BankAccount sbi = new BankAccount();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                sbi.withdraw(150);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

    }
}
