import java.util.ArrayList;
import java.util.List;

public class Printing {

    public static void main(String[] args) throws InterruptedException {
        PrintersMonitor m = new PrintersMonitor();
        List<Printer> printers = new ArrayList<>();
        List <Thread> printersThreads = new ArrayList<>();
        for(int i=0; i<7; i++) printers.add(new Printer(m));
        for(int i=0; i<7; i++){
            printersThreads.add(new Thread(printers.get(i)));
            printersThreads.get(i).start();
        }
        for(int i=0; i<7; i++) printersThreads.get(i).join();
    }

}
