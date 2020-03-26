public class Buffer {

    private String message;
    private boolean isEmpty = true;

    public synchronized void put(String mes){

        while(!isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isEmpty = false;
        message = mes;
        System.out.println("Produced: " + mes);
        notifyAll();
    }

    public synchronized String take(){

        while(isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isEmpty = true;
        System.out.println("Consumed: " + message);
        notifyAll();
        return message;
    }
}
