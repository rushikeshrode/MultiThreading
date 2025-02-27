package Chapter_05;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//
public class ScheduledThread {
    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

//        scheduler.schedule(()->System.out.println("hello after 5 secs..."),
//            5,TimeUnit.SECONDS);
//
//        scheduler.shutdown();

        scheduler.scheduleAtFixedRate(()-> System.out.println("hello after every 5 secs"),
                5, 5, TimeUnit.SECONDS);

        scheduler.schedule(()-> {
            System.out.println("Shutdown in-progress ...");
            scheduler.shutdown();
        }, 20, TimeUnit.SECONDS);

    }
}
