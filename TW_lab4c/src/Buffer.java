import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private int elementsCount;
    private int M;
    private Integer[] buffer = new Integer[2*M];
    private final Lock lock = new ReentrantLock();
    private Condition canProduce = lock.newCondition();
    private Condition canConsume = lock.newCondition();
    private Condition canTryProducing = lock.newCondition();
    private Condition canTryConsuming = lock.newCondition();
    private boolean underConsumption = false;
    private boolean underProduction = false;

    public Buffer(int _M){
        this.M = _M;
        this.elementsCount = 0;
        for(int i=0; i<buffer.length; i++) buffer[i] = -1;
    }

    public void put(int amount) throws InterruptedException {
        lock.lock();
        while(underProduction) canTryProducing.await();
        underProduction = true;
        //System.out.println("Chcę wyprodukować elementów: " + amount);
        while(2*M - elementsCount < amount) canProduce.await();
        elementsCount += amount;
        //System.out.println("Wyprodukowano elementów: " + amount + ", liczba elementów w buforze: " + elementsCount);
        canConsume.signal();
        underProduction = false;
        canTryProducing.signal();
        lock.unlock();
    }

    public void take(int amount) throws InterruptedException {
        lock.lock();
        while(underConsumption) canTryConsuming.await();
        underConsumption = true;
        //System.out.println("Chcę pobrać elementów: " + amount);
        while(elementsCount < amount) canConsume.await();
        elementsCount -= amount;
        //System.out.println("Pobrano elementów: " + amount + ", liczba elementów w buforze: " + elementsCount);
        canProduce.signal();
        underConsumption = false;
        canTryConsuming.signal();
        lock.unlock();
    }

}
