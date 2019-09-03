package com.masonluo.Parallelism.chapter5.cyclicBarrier;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args){
        int N = 5000000;
        CountDownLatch countDownLatch;
        int threads = 2;
        int[] array = new int[N];
        for(int i = 0; i < N; i++){
            array[i] = (int) (Math.random() * N);
        }
        System.out.println("数据初始化成功");
        int[] data =  new int[threads + 1];
        int slice = N / threads;
        for(int i = 0; i <= threads; i++){
            data[i] = slice * i;
            if(data[i] > N){
                data[i] = N;
            }
        }
        long start = System.currentTimeMillis();
        int[][] subAry = new int[threads][slice];
        for(int i = 0; i < threads; i++){
            subAry[i] = Arrays.copyOfRange(array, data[i], data[i + 1]);
        }
        System.out.println("数据划分成功");
        Thread[] t = new Thread[threads];
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threads + 1);
        for(int i = 0; i < threads; i++){
            t[i] = new Worker(subAry[i], cyclicBarrier);
            t[i].start();
        }
        System.out.println(threads + "个线程已经启动");
        try{
            cyclicBarrier.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        array = converge(subAry[0], subAry[1]);
        if(check(array)){
            System.out.println("数据排序成功");
        }else{
            System.out.println("数据排序失败");
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间"  + (end - start));
    }
    private static int[] converge(int[] arr1, int[] arr2){
        int[] arr = new int[arr1.length + arr2.length];
        int i1 = 0, i2 = 0, i = 0;
        while (i1 < arr1.length && i2 < arr2.length){
            if(arr1[i1] < arr2[i2]){
                arr[i] = arr1[i1];
                i++;
                i1++;
            }else{
                arr[i] = arr2[i2];
                i++;
                i2++;
            }
        }
        while(i1 < arr1.length){
            arr[i] = arr1[i1];
            i++;
            i1++;
        }


        while(i2 < arr2.length){
            arr[i] = arr2[i2];
            i++;
            i2++;
        }
        return arr;
    }

    private static boolean check(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] > arr[i + 1]){
                return false;
            }
        }
        return true;
    }

}
