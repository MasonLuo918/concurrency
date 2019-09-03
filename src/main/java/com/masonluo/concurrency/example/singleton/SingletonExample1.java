package com.masonluo.concurrency.example.singleton;


import com.masonluo.concurrency.annotation.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用的时候被创建
 */
@NotThreadSafe
public class SingletonExample1 {

    // 私有化构造函数
    private SingletonExample1(){

    }

    // 单例模式对象
    private static SingletonExample1 instance = null;

    // 静态的工厂方法
    public static SingletonExample1 getInstance(){
        // 单线程的情况下没问题，多线程就会出问题
        if(instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
