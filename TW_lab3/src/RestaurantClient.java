import java.util.concurrent.TimeUnit;

public class RestaurantClient extends Thread {

    private Servant servant;
    private int pairNumber;

    public RestaurantClient(Servant s, int nr){
        this.servant = s;
        this.pairNumber = nr;
    }

    public void run(){
        while(true) {
            try {
                TimeUnit.SECONDS.sleep((long)(Math.random() * 5));
                servant.wantTable(pairNumber);
                TimeUnit.SECONDS.sleep((long)(Math.random() * 5));
                servant.free();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
