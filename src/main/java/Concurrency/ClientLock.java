package Concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientLock {



    public static void main(String[] args) {
        ListHelper<Integer> listHelper = new ListHelper<>();

        Thread thread = new Thread(){
            @Override
            public void run() {
//                listHelper.putIfAbsent(1);
                listHelper.t();
            }
        };
        thread.start();

//        listHelper.t();
        listHelper.t1();
    }
}

class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public synchronized void t(){
        System.out.println(2);
        for (int i = 0;i<100000;i++){
            int c =i;
        }
        System.out.println(2);
    }

    public void t1(){
        System.out.println(4);
    }

    public boolean putIfAbsent(E e){
        System.out.println(1);
        for (int i = 0;i<100000;i++){
            int c =i;
        }
        synchronized (list) {
            System.out.println(3);
            boolean a = !list.contains(e);
            for (int i = 0;i<100000;i++){
                int c =i;
            }
            if(a)
                list.add(e);
            return a;
        }
    }
}