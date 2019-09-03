package com.masonluo.Parallelism.chapter5.semphore;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import javax.annotation.security.RunAs;
import java.util.concurrent.Semaphore;

public class Worker implements Runnable {
    private Data data;

    Worker(Data data){
        this.data = data;
    }

    @Override
    public void run(){
        while(true){
            data.increase();
        }
    }

    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(1);
        Data data = new Data(semaphore);
        Worker worker1 = new Worker(data);
        Worker worker2 = new Worker(data);
        Thread t1 = new Thread(worker1);
        Thread t2 = new Thread(worker2);
        t1.start();
        t2.start();
        while(true){
            data.isEqual();
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
