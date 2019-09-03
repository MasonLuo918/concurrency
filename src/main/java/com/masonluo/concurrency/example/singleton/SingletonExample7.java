package com.masonluo.concurrency.example.singleton;


import com.masonluo.concurrency.annotation.Recommend;
import com.masonluo.concurrency.annotation.ThreadSafe;

/**
 * 枚举模式，最安全有效
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    // 私有化构造函数
    private SingletonExample7(){

    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;
        // JVM保证这个方法只被调用一次，绝对的
        Singleton(){
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getInstance(){
            return singleton;
        }
    }
}
