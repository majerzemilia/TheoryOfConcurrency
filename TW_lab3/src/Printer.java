import java.util.concurrent.TimeUnit;

public class Printer extends Thread {

    private PrintersMonitor monitor;
    public Printer(PrintersMonitor m){this.monitor = m;}

    public void run(){
        while(true) {
            try {
                TimeUnit.SECONDS.sleep((long) Math.random() % 10 + 3);
                int printer_nr = 0;
                printer_nr = monitor.reserve();
                System.out.println("Drukuje na drukarce nr " + printer_nr);
                monitor.free(printer_nr);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
