public class BinarySemaphore {

    private boolean isOpen;
    public BinarySemaphore(boolean _isOpen){
        this.isOpen = _isOpen;
    }

    public synchronized void take(){

        while(!this.isOpen){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isOpen = false;
    }

    public synchronized void giveAway(){

        this.isOpen = true;
        notifyAll();
    }
}
