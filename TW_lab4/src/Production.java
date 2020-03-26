import java.util.ArrayList;
import java.util.List;

public class Production {

    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Thread p = new Thread(new Producer(buffer));
        List<Thread> processers = new ArrayList<>();
        for(int i=0; i<5; i++){
            processers.add(new Thread(new Processer(buffer, i+1)));
        }
        Thread c = new Thread(new Consumer(buffer));

        p.start();
        for(Thread t: processers) t.start();
        c.start();

        p.join();
        for(Thread t: processers) t.join();
        c.join();
    }
}
