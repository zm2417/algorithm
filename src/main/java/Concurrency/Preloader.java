package Concurrency;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.concurrent.*;

public class Preloader {

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在计算");
            Thread.sleep(1000);
            int sum = 0;
            for (int i = 0; i <100;i++){
                sum += i;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        executorService.submit(futureTask);

        System.out.println("主线程正在执行任务");

        try {
            System.out.println("task 结果: " + futureTask.get());
            System.out.println("task 结果: " + futureTask.get());
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        System.out.println("所有任务执行完成");
        executorService.shutdown();
    }
}
