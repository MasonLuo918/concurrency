package com.masonluo.concurrency.example.singleton;


import com.masonluo.concurrency.annotation.NotRecommend;
import com.masonluo.concurrency.annotation.NotThreadSafe;
import com.masonluo.concurrency.annotation.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用的时候被创建
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    // 私有化构造函数
    private SingletonExample3(){

    }

    // 单例模式对象
    private static SingletonExample3 instance = null;

    // 静态的工厂方法
    public static synchronized SingletonExample3 getInstance(){
        // 单线程的情况下没问题，多线程就会出问题
        if(instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
