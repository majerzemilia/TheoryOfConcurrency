public class Consumer implements Runnable {
    private Buffer buffer;


    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        for(int i = 0;  i < 8;   i++) {
            String message = (String)buffer.take();
        }

    }
}
