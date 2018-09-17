package util;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

public class AtomicTest {

    public static void main(String[] args){
//        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Dog.class,String.class,"name");
//        Dog dog = new Dog();
//        atomicReferenceFieldUpdater.compareAndSet(dog,dog.name,"pig");
//        System.out.println(dog.name);
        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left+right;
            }
        },0);
        longAccumulator.accumulate(1);
        System.out.println(longAccumulator.get());
    }
}

class Dog{
    volatile String name = "dog";
}