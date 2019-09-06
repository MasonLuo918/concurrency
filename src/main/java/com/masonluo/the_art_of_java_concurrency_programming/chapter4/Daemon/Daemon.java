package com.masonluo.the_art_of_java_concurrency_programming.chapter4.Daemon;

import com.masonluo.concurrency.annotation.ThreadSafe;
import com.masonluo.the_art_of_java_concurrency_programming.chapter4.example4_3.SleepUtils;

public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(),"DaemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable{

        @Override
        public void run() {
            try{
                SleepUtils.second(10);
            }finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
