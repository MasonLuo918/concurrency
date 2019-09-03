package com.masonluo.concurrency.example.singleton;


import com.masonluo.concurrency.annotation.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类被装载时候被创建
 */
@ThreadSafe
public class SingletonExample6 {

    // 私有化构造函数
    private SingletonExample6(){

    }
    static {
        instance = new SingletonExample6();
    }

    // 单例模式对象
    private static SingletonExample6 instance = null;


    // 静态的工厂方法
    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args){
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
