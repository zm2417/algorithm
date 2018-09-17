package util;

public class SynchronizedTest {

    public static void main(String[] args){
        Reentrant reentrant = new Reentrant();
        ThreadTest threadTest = new ThreadTest(reentrant);
        ThreadTest threadTest1 = new ThreadTest(reentrant);
        Thread thread = new Thread(threadTest);
        Thread thread1 = new Thread(threadTest1);
        thread.start();
        thread1.start();
    }

}

class ThreadTest implements Runnable{

    private Reentrant reentrant;

    public ThreadTest(Reentrant reentrant) {
        this.reentrant = new Reentrant();
    }

    @Override
    public void run() {
        reentrant.outer();
    }

}

class Reentrant {
    public synchronized void outer(){
        System.out.println("in outer");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inner();
        System.out.println("out outer");
    }

    public synchronized void inner(){
        System.out.println("inner");
    }
}
