package com.masonluo.Parallelism.chapter3.reentrantReadWriteLock;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        int numThreads = 10;

        int readNum = 1;

        int exeTimes = 50000;

        List<Integer> myInteger = new ArrayList<>();

        for(int i = 0; i < readNum; i++){
            for(int j = 0; j < exeTimes; j++){
                myInteger.add(i * exeTimes + j);
            }
        }
        ReenTest reenTest = new ReenTest(myInteger);
        long startTime = System.currentTimeMillis();
        Thread[] rd = new ReadThread[readNum];
        int writeNum = numThreads - readNum;
        Thread[]wr = new WriteThread[writeNum];
        for(int i = 0; i < readNum; i++){
            rd[i] = new ReadThread(i, reenTest, exeTimes);
            rd[i].start();
        }
        System.out.println("读线程已经启动");
        for(int i = 0; i < writeNum; i++){
            wr[i] = new WriteThread(readNum + i, reenTest, exeTimes);
            wr[i].start();
        }
        System.out.println("写线程已启动");
        try{
            for(int i = 0; i < readNum; i++){
                rd[i].join();
            }
            for(int i = 0; i < writeNum; i++){
                wr[i].join();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("花费时间:" + (endTime - startTime));
    }
}
