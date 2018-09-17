package Concurrency;

import java.util.concurrent.locks.Lock;

public class TwinsLockTest {

    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new TwinsLock();

        class Work extends Thread{
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                        System.out.println("unlock - "+Thread.currentThread());
                    }
                }
            }
        }

        for (int i = 0;i<10;i++){
            Work work = new Work();
            work.start();
        }

        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(200);
                    System.out.println("as");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(20000);
    }

}
