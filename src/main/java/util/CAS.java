package util;

import java.util.concurrent.atomic.AtomicInteger;

public class CAS {

    public static void main(String[] args){
        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean b = atomicInteger.compareAndSet(5,2);
        int i = atomicInteger.get();
        System.out.println(b+" "+i);
    }

}
