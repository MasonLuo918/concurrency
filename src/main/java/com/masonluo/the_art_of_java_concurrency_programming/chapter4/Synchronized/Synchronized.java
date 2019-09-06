package com.masonluo.the_art_of_java_concurrency_programming.chapter4.Synchronized;

public class Synchronized {
    // 对Synchronized对象进行加锁
    public static void main(String[] args) {
        synchronized (Synchronized.class){

        }
        m();
        // 静态同步方法，对象对对Synchronized加锁
    }

    public static synchronized void m(){

    }

}
