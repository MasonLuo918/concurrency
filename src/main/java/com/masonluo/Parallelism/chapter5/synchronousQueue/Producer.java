package com.masonluo.Parallelism.chapter5.synchronousQueue;

import java.util.concurrent.SynchronousQueue;

public class Producer extends Thread {
    SynchronousQueue<Integer> queue;

    Producer(SynchronousQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run(){
        for(int i = 0; i < 5; i++){
            int data = (int)(Math.random() * 100);
            System.out.println("生产者生产了一个数据:" + data);
            try{
                queue.put(data);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
