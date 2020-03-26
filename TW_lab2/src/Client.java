import java.util.concurrent.TimeUnit;

public class Client extends Thread {

    private CountingSemaphore sem;
    public Client(CountingSemaphore _sem){
        this.sem = _sem;
    }
    public void run(){
        sem.take();
        int shoppingTime = (int) Math.random()%10 + 3;
        try {
            TimeUnit.SECONDS.sleep(shoppingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sem.giveAway();
    }
}
