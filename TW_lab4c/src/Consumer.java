import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private int M;
    private Buffer buffer;
    private long time;
    private static int lines = 0;
    FileWriter fw = new FileWriter("times4.csv", true);
    BufferedWriter bw = new BufferedWriter(fw);
    PrintWriter writer = new PrintWriter(bw);

    public Consumer(int _M, Buffer buffer) throws IOException {
        this.M = _M;
        this.buffer = buffer;
    }

    public void run(){
        while(true){
            try {
                time = System.nanoTime();
                int amount = ThreadLocalRandom.current().nextInt(1, M + 1);
                buffer.take(amount);
                time = System.nanoTime() - time;
                if(lines <= 100000) {
                    writer.println("K " + amount + " " + time);
                    writer.flush();
                    lines++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}