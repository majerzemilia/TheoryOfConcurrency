public class Producer implements Runnable {
    private Buffer buffer;
    private double speed = Math.random() * 5;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        while(true) {
            for (int i = 0; i < 10; i++) {
                try {
                    buffer.produce(i, speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}