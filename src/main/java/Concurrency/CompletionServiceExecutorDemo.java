package Concurrency;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceExecutorDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CompletionService<String> completionService = new ExecutorCompletionService<>(threadPool);
        for (int i = 0;i<10;i++){
            final int seq = i;
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    int waitTime = new Random().nextInt(10);
                    TimeUnit.SECONDS.sleep(waitTime);
                    return "callable:" + seq + "执行时间:" + waitTime+"s";
                }
            });
        }
        for (int i = 1;i<=10;i++){
            try {
                Future<String> future = completionService.take();
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
    }

}
