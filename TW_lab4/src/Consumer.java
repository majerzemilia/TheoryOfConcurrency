public class Consumer implements Runnable {
    private Buffer buffer;
    private double speed = Math.random() * 5;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        while(true) {
            for (int i = 0; i < 10; i++) {
                try {
                    buffer.consume(i, speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}