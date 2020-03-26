import java.util.ArrayList;
import java.util.List;

public class Dinner {

    public static void main(String[] args) throws InterruptedException {

        BoundedBuffer b = new BoundedBuffer();
        List<Producer> producers = new ArrayList<>();
        for (int i=0; i<4; i++) producers.add(new Producer(b));
        List<Thread> threads = new ArrayList<>();
        for(int i=0; i<4; i++){
            threads.add(new Thread(producers.get(i)));
            threads.get(i).start();
        }
        List <Consumer> consumers = new ArrayList<>();
        for (int i=0; i<4; i++) consumers.add(new Consumer(b));
        for(int i=0; i<3; i++){
            threads.add(new Thread(consumers.get(i)));
            threads.get(i+4).start();
        }
        for(int i=0; i<7; i++) threads.get(i).join();

    }
}
