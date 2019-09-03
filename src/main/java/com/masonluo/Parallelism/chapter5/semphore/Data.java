package com.masonluo.Parallelism.chapter5.semphore;

import java.util.concurrent.Semaphore;

public class Data {
    int a = 0;
    int b = 0;
    Semaphore semaphore;
    Data(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    public void increase(){
        try{
            semaphore.acquire();
            a++;
            b++;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        semaphore.release();
    }

    public void isEqual(){
        try{
            semaphore.acquire();
            System.out.println("a = " + a + "\t b = " + b + "\t" + (a==b));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        semaphore.release();
    }
}
