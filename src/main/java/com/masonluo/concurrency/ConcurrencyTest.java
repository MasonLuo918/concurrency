package com.masonluo.concurrency;

import com.masonluo.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    public static final Logger log = LoggerFactory.getLogger(ConcurrencyTest.class);

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 20;

    public static int count = 0;

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
                    add();
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
        log.info("count:{}", count);
    }

    private static void add(){
        count++;
    }
}
