public class Counter {

    private int number = 0;
    //private BinarySemaphore sem = new BinarySemaphore(true);

    //public synchronized  void increment(){
    public void increment(){
        //sem.take();
        number++;
        //sem.giveAway();
    }

    //public synchronized void decrement(){
    public void decrement(){
        //sem.take();
        number--;
        //sem.giveAway();
    }

    //public synchronized void getNumber(){
    public void getNumber(){
        System.out.println(number);
    }

}
