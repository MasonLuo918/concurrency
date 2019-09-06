package com.masonluo.the_art_of_java_concurrency_programming.chapter4.ThreadLocal;

import sun.java2d.cmm.Profile;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal， 一个线程变量， 这个结构被附带在线程上， 也就是说一个线程
 * 可以根据一个ThreadLocal对象查询到绑定在这个线程上的一个值
 * 可以通过set(T)来设置一个值，在当前线程下再通过get()方法获取到原来的值
 */
public class Profiler {
    // 第一个get方法调用时会进行初始化(如果set方法没有调用)
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue(){
            return System.currentTimeMillis();
        }
    };

    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final Long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end() + " mills");
    }
}
