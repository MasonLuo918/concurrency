package com.masonluo.the_art_of_java_concurrency_programming.chapter4.example4_3;


import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.TimeUnit;

public class SleepUtils {
    public static final void second(long seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
