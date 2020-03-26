public class DecThread extends Thread {

    private Counter c;
    public DecThread(Counter _c){
        c = _c;
    }
    public void run(){
        for(int i=0; i<1000000; i++)
            c.decrement();
    }
}
