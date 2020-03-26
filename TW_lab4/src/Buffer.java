import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private int bufferSize = 10;
    private final Lock[] lock = new ReentrantLock[bufferSize];
    private Integer[] buffer = new Integer[bufferSize];
    private Condition canProduce[] = new Condition[bufferSize];
    private Condition canConsume[] = new Condition[bufferSize];
    private Condition canProcess[] = new Condition[bufferSize];
    private int processersCount = 5;


    public Buffer(){
        for (int i=0; i<buffer.length; i++){
            lock[i] = new ReentrantLock();
            buffer[i] = -1;
            canProduce[i] = lock[i].newCondition();
            canConsume[i] = lock[i].newCondition();
            canProcess[i] = lock[i].newCondition();
        }
    }

    public void produce(int i, double speed) throws InterruptedException {
        lock[i].lock();
        TimeUnit.SECONDS.sleep((long)speed);
        while(!buffer[i].equals(-1)) canProduce[i].await();
        buffer[i] = 0;
        System.out.println("Wyprodukowano komórkę nr " + i);
        canProcess[i].signalAll();
        lock[i].unlock();
    }

    public void process(int i, int producerNr, double speed) throws InterruptedException {
        lock[i].lock();
        TimeUnit.SECONDS.sleep((long)speed);
        while(!buffer[i].equals(producerNr-1)) canProcess[i].await();
        buffer[i] = producerNr;
        System.out.println("Przetworzono komórkę nr " + i + " przez przetwórcę nr " + producerNr);
        canProcess[i].signalAll();
        if(producerNr == processersCount) canConsume[i].signal();
        lock[i].unlock();
    }

    public void consume(int i, double speed) throws InterruptedException {
        lock[i].lock();
        TimeUnit.SECONDS.sleep((long)speed);
        while(!buffer[i].equals(processersCount)) canConsume[i].await();
        buffer[i] = -1;
        System.out.println("Skonsumowano komórkę nr " + i);
        canProduce[i].signal();
        lock[i].unlock();
    }
}
