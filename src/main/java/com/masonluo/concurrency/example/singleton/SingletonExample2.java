package com.masonluo.concurrency.example.singleton;


import com.masonluo.concurrency.annotation.NotThreadSafe;
import com.masonluo.concurrency.annotation.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类被装载时候被创建
 */
@ThreadSafe
public class SingletonExample2 {

    // 私有化构造函数
    private SingletonExample2(){

    }

    // 单例模式对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态的工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
