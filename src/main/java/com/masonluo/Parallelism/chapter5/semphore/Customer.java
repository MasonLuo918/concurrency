package com.masonluo.Parallelism.chapter5.semphore;

import java.util.concurrent.Semaphore;

public class Customer extends Thread {
    Semaphore counter;

    int id;

    int times;

    Customer(Semaphore counter, int id){
        this.counter = counter;
        this.id = id;
        times = (int)(Math.random() * Integer.MAX_VALUE);
    }

    @Override
    public void run(){
        try{
            counter.acquire();
            System.out.println("第" + id + "位顾客开始办理业务...");
            for(int i = 0; i < times; i++);
            System.out.println("第" + id + "位顾客已经办理完业务，准备离开");
            counter.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
