import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintersMonitor {

    private final Lock lock = new ReentrantLock();
    private final Boolean[] printers = new Boolean[4];
    private int count;
    private final Condition notAllTaken = lock.newCondition();

    public PrintersMonitor(){
        for(int i=0; i<printers.length; i++) printers[i] = true;
    }

    public int reserve() throws InterruptedException {
        lock.lock();
        int x = -1;
        while (count == printers.length) notAllTaken.await();
        for(int i=0; i<printers.length; i++){
            if (printers[i]){
                x = i;
                break;
            }
        }
        printers[x] = false;
        ++count;
        System.out.println("Zarezerwowano drukarke nr " + x);
        lock.unlock();
        return x;
    }

    public void free(int numer){
        lock.lock();
        printers[numer] = true;
        --count;
        notAllTaken.signal();
        System.out.println("Zwolniono drukarke nr " + numer);
        lock.unlock();
    }
}
