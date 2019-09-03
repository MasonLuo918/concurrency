package com.masonluo.Parallelism.chapter4.producer_consumer;

public class Main {
    public static void main(String[] args){
        CubbyHole cubbyHole = new CubbyHole();
        Producer producer = new Producer(cubbyHole);
        producer.start();
        Consumer consumer = new Consumer(cubbyHole);
        consumer.start();
    }
}
