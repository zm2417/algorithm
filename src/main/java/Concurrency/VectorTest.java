package Concurrency;

import java.util.Vector;

public class VectorTest {
    public static void main(String[] args) throws Exception {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);vector.add(2);vector.add(3);

        Thread thread = new Thread(){
            @Override
            public void run() {
                for (int i : vector){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                }
            }
        };
        thread.start();
        System.out.println("asd");
        vector.remove(vector.size()-1);

    }
}
