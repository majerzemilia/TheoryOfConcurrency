import java.util.ArrayList;
import java.util.List;

public class SelfServiceShop {

    public static void main(String[] args) throws InterruptedException {

        List<Client> clients = new ArrayList<>();
        CountingSemaphore s = new CountingSemaphore(4);
        for(int i=0; i<10; i++){
            Client c = new Client(s);
            clients.add(c);
            c.start();
        }
        for(Client c: clients){
            c.join();
        }
    }
}
