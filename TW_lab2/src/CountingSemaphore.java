public class CountingSemaphore {

    private int val;
    public CountingSemaphore(int _val){
        this.val = _val;
    }

    public synchronized void take(){
        while(this.val < 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.val--;
        System.out.println("Pobrano koszyk");
    }

    public synchronized void giveAway(){
        this.val++;
        notifyAll();
        System.out.println("Oddano koszyk");
    }
}
