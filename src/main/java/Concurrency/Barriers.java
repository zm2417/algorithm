package Concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活.
 *
 * CyclicBarrier可以用于多线程计算数据，最后合并计算结果的应用场景。
 * 比如我们用一个Excel保存了用户所有银行流水，每个Sheet保存一个帐户近一年的每笔银行流水，
 * 现在需要统计用户的日均银行流水，先用多线程处理每个sheet里的银行流水，都执行完之后，
 * 得到每个sheet的日均银行流水，最后，再用barrierAction用这些线程的计算结果，计算出整个Excel的日均银行流水。
 *
 * diff CyclicBarrier and CountDownLatch
 * 1.CountDownLatch的计数器只能使用一次。而CyclicBarrier的计数器可以使用reset() 方法重置。
 * 所以CyclicBarrier能处理更为复杂的业务场景，比如如果计算发生错误，可以重置计数器，并让线程们重新执行一次。
 * 2.CyclicBarrier还提供其他有用的方法，比如getNumberWaiting方法可以获得CyclicBarrier阻塞的线程数量。
 * isBroken方法用来知道阻塞的线程是否被中断。比如以下代码执行完之后会返回true。
 */
public class Barriers {

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2,new A());

    static class A implements Runnable {

        @Override
        public void run() {
            System.out.println(3);
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }
}
