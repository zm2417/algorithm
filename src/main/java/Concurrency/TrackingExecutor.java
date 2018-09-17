package Concurrency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TrackingExecutor extends AbstractExecutorService {
    private final ExecutorService exec;
    private final Set<Runnable> trackCancelledAtShutdown = new HashSet<>();

    public TrackingExecutor(ExecutorService e){
        this.exec = e;
    }

    public List<Runnable> getCancelledTasks(){
        if(!exec.isTerminated()){
            throw new IllegalArgumentException("no terminated!");
        }
        return new ArrayList<>(trackCancelledAtShutdown);
    }

    @Override
    public void execute(Runnable command) {
        exec.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    command.run();
                }finally {
                    if(isShutdown() && Thread.currentThread().isInterrupted())
                        trackCancelledAtShutdown.add(command);
                }
            }
        });
    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }


}
