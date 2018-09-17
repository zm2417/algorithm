package Concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinsLock implements Lock {

    private Sync sync = new Sync(2);

    private static class Sync extends AbstractQueuedSynchronizer{
        private static final long   serialVersionUID    = -7889272986162341211L;

        Sync(int count){
            if(count <= 0) throw new IllegalArgumentException("count must larger than zero.");
            setState(count);
        }

        public int tryAcquireShared(int reduceCount){
            for (;;){
                int count = getState();
                int newCount = count - reduceCount;
                if(newCount < 0 || compareAndSetState(count,newCount)){
                    return newCount;
                }
            }
        }

        public boolean tryReleaseShared(int returnCount){
            for (;;){
                int count = getState();
                int newCount = count + returnCount;
                if(compareAndSetState(count,newCount)){
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
