package com.masonluo.Parallelism.chapter6.example1;

public class Worker implements Runnable {

    Worker(String name){
        Thread.currentThread().setName(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 正在努力完成自己的工作");
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
