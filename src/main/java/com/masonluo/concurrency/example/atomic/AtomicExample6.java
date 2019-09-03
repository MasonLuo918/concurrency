package com.masonluo.concurrency.example.atomic;

import com.masonluo.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample6 {
    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 20;

    public static void main(String[] args) throws InterruptedException {
        // 定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 信号量，传入同时并发的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 传入请求总数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("exception", e);
                }
                countDownLatch.countDown();;
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", isHappened.get());
    }

    private static void test(){
        if(isHappened.compareAndSet(false, true)){
            log.info("execute");
        }
    }
}
