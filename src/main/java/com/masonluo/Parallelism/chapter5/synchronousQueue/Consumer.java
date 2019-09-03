package com.masonluo.Parallelism.chapter5.synchronousQueue;

import java.util.concurrent.SynchronousQueue;

public class Consumer extends Thread {
    SynchronousQueue<Integer> queue;
    Consumer(SynchronousQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run(){
        for(int i = 0; i < 5; i++){
            try{
                System.out.println("消费者消费了一个数据:" + queue.take());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
