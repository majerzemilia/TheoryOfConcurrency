public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        IncThread ithread = new IncThread(c);
        DecThread dthread = new DecThread(c);
        ithread.start();
        dthread.start();
        ithread.join();
        dthread.join();
        c.getNumber();
    }
}
