package com.masonluo.Parallelism.chapter5.synchronousQueue;

import java.util.concurrent.SynchronousQueue;

public class Main {
    public static void main(String[] args){
        // SynchronousQueue是这样 一种阻塞队列，其中每个 put 必须等待一个 take，反之亦然。同步队列没有任何内部容量，甚至连一个队列的容量都没有。
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        Thread producer = new Producer(queue);
        Thread consumer = new Consumer(queue);
        producer.start();
        consumer.start();
    }
}
