import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    public static void main(String[] args) throws InterruptedException {
        Servant s = new Servant();
        List <Thread> threads = new ArrayList<>();
        for(int i=0; i<5; i++){
            threads.add(new Thread((new RestaurantClient(s, i))));
            threads.add(new Thread((new RestaurantClient(s, i))));
        }

        for(Thread thread: threads) thread.start();
        for(Thread thread: threads) thread.join();


    }
}
