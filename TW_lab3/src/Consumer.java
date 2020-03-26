public class Consumer implements Runnable {
    private BoundedBuffer buffer;


    public Consumer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        for(int i = 0;  i < 8;   i++) {
            try {
                String message = (String)buffer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
