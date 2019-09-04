package com.masonluo.Parallelism.chapter8.Executor;

public class Worker implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始工作");
        doSomething();
        System.out.println(Thread.currentThread().getName() + "工作结束");
    }

    private void doSomething(){
        int number = (int)(Math.random() * 100);
        for(int i = 0, fac = 1; i < number; i++){
            fac = fac * i;
        }
    }
}
