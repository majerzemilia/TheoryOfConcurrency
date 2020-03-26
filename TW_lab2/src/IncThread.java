public class IncThread extends Thread {

    private Counter c;
    public IncThread(Counter _c){
        c = _c;
    }
    public void run(){
        for(int i=0; i<1000000; i++)
            c.increment();
    }

}
