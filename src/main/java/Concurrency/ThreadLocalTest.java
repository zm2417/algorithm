package Concurrency;

public class ThreadLocalTest {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();
    ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    public long getLong(){
        return longThreadLocal.get();
    }

    public String getString(){
        return stringThreadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalTest test = new ThreadLocalTest();
        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());

        Thread thread = new Thread(){
            @Override
            public void run() {
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }
        };
        thread.start();
        thread.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
