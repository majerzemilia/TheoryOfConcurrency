import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Production {

    public static void main(String[] args) throws InterruptedException, IOException {
        int M = 100000;
        Buffer buffer = new Buffer(M);
        List<Thread> producers = new ArrayList<>();
        List<Thread> consumers = new ArrayList<>();
        for(int i=0; i<1000; i++){
            producers.add(new Thread(new Producer(M, buffer)));
        }
        for(int i=0; i<1000; i++){
            consumers.add(new Thread(new Consumer(M, buffer)));
        }

        for(Thread t: producers) t.start();
        for(Thread t: consumers) t.start();

        for(Thread t: producers) t.join();
        for(Thread t: consumers) t.join();
    }
}
