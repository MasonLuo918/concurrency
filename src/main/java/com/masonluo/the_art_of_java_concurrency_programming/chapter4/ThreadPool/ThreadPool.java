package com.masonluo.the_art_of_java_concurrency_programming.chapter4.ThreadPool;

public interface ThreadPool<Job extends Runnable> {
    // 执行一个Job，这个Job需要实现Runnable接口
    void execute(Job job);

    // 关闭线程池
    void shutdown();

    // 增加工作者线程
    void addWorkers(int num);

    // 减少工作者线程
    void removeWorkers(int num);

    // 得到正在等待执行的任务数量
    int getJobSize();
}
