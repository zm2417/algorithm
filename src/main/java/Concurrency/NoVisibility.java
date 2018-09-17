package Concurrency;

public class NoVisibility {

    private static boolean ready;
    private static int number;

    public static class Reader extends Thread {
        public void run(){
            if(!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new Reader().start();
        number = 42;
        ready = true;
    }
}
