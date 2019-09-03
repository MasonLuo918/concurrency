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
public class SingletonExample4 {

    // 私有化构造函数
    private SingletonExample4() {

    }

    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance只想刚分配的内存

    // JVM和CPU优化，发生了指令重排

    // 1、memory = allocate() 分配对象的内存空间
    // 3、instance = memory 设置instance只想刚分配的内存
    // 2、ctorInstance() 初始化对象

    // 单例模式对象
    // volatile 限制指令重排
    // volatile + 双重检测机制 -> 限制指令重排
    private volatile static SingletonExample4 instance = null;

    // 静态的工厂方法
    public static synchronized SingletonExample4 getInstance() {
        // 单线程的情况下没问题，多线程就会出问题
        if (instance == null) { // 双重检测机制  // 线程b认为不为null，直接返回，就会出现问题
            synchronized (SingletonExample4.class) { // 同步锁
                if(instance == null){
                    instance = new SingletonExample4(); // 线程A执行到这里，指令重排的3，调度线程b
                }
            }
        }
        return instance;
    }
}
