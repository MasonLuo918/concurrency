package com.masonluo.Parallelism.chapater2.threadGroup;

public class Worker implements Runnable {

    int counter = 0;

    @Override
    public void run() {
        for(int i = 0; i < Integer.MAX_VALUE; i++){
            counter++;
        }
        System.out.println(Thread.currentThread().getName() + "已经终止");
    }

    public static void main(String[] args){
        int threadNum = 5;
        ThreadGroup parentGroup = new ThreadGroup("父线程组");
        ThreadGroup childGroup = new ThreadGroup(parentGroup,"子线程组");
        Worker worker = new Worker();
        Thread[] threads = new Thread[threadNum * 2];
        for(int i = 0; i < threadNum; i++){
            threads[i] = new Thread(childGroup, worker);
            threads[i].start();
        }
        System.out.println(threadNum + "个线程被加入子线程组");
        for(int i = 0; i < threadNum; i++){
            threads[i + threadNum] = new Thread(parentGroup, worker);
            threads[i + threadNum].start();
        }
        System.out.println(threadNum + "个线程被加入父线程组");
        System.out.println("在" + parentGroup.getName() + "中活动线程数为:" + parentGroup.activeCount());
        childGroup.stop();
        System.out.println("子线程组已经被停止");
        parentGroup.stop();
    }
}
