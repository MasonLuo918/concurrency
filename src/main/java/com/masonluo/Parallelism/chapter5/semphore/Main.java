package com.masonluo.Parallelism.chapter5.semphore;

import java.util.concurrent.Semaphore;

public class Main  {
    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(4);
        Thread[] th = new Thread[20];
        for(int i = 0; i < 20; i++){
            th[i] = new Customer(semaphore, i + 1);
            th[i].start();
        }
    }
}
