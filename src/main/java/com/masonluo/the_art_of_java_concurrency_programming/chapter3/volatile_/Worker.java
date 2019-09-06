package com.masonluo.the_art_of_java_concurrency_programming.chapter3.volatile_;

public class Worker implements Runnable {

    private VolatileFeaturesExample example;

    public Worker(){
        example = new VolatileFeaturesExample();
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            example.getAndIncrement();
            System.out.println(Thread.currentThread().getName() + "修改后的值为" + example.get());
        }

        System.out.println(Thread.currentThread().getName() + "最后的值为:" + example.get());
    }
}
