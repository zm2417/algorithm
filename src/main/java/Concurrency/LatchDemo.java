package Concurrency;

import java.util.concurrent.CountDownLatch;

public class LatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);

        for (int i = 0;i<5;i++){
            Thread t = new Thread(){
                @Override
                public void run() {
                    try {
                        System.out.println("线程等待");
                        start.await();
                        System.out.println("线程");
                        Thread.sleep(100);
                        end.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }

        start.countDown();
        end.await();
    }
}
