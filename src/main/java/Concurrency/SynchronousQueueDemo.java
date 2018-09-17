package Concurrency;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    public static void main(String[] args) throws Exception {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("start "+1);
                    synchronousQueue.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("put thread end 1");
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("start "+2);
                    synchronousQueue.put(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("put thread end 2");
            }
        };

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("take thread end");
            }
        };

        Thread thread4 = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("take thread end");
            }
        };

        thread.start();
        thread2.start();
        Thread.sleep(1000);
        thread1.start();
        thread4.start();
    }
}
