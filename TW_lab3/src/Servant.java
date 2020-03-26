import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Servant {

    private final ReentrantLock lock = new ReentrantLock();
    private Condition[] pair = new Condition[5];
    private int atTheTable = 0;
    private final Condition freeTable = lock.newCondition();

    public Servant(){
        for (int i=0; i<pair.length; i++) pair[i] = lock.newCondition();
    }

    public void wantTable(int j) throws InterruptedException {
        lock.lock();
        if(!lock.hasWaiters(pair[j])) pair[j].await();
        else{
            while (atTheTable != 0) freeTable.await();
            atTheTable = 2;
            System.out.println("Zajęto stolik przez parę nr " + j);
            pair[j].signal();
        }
        lock.unlock();
    }

    public void free(){
        lock.lock();
        if (--atTheTable == 0){
            System.out.println("Wolny stolik!");
            freeTable.signal();
        }
        lock.unlock();
    }

}
