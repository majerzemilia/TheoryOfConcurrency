public class Processer implements Runnable {
    private Buffer buffer;
    private int number;
    private double speed = Math.random() * 3;

    public Processer(Buffer buffer, int number) {
        this.buffer = buffer;
        this.number = number;
    }

    public void run() {
        while(true) {
            for (int i = 0; i < 10; i++) {
                try {
                    buffer.process(i, this.number, this.speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
