package com.masonluo.the_art_of_java_concurrency_programming.chapter3;

import com.masonluo.concurrency.annotation.ThreadSafe;

@ThreadSafe
public class InstanceFactory {
    public static class InstanceHolder{
        public static InstanceFactory instance = new InstanceFactory();
    }

    public static InstanceFactory getInstance(){
        return InstanceHolder.instance;
    }
}
