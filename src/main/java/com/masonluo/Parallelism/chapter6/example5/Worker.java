package com.masonluo.Parallelism.chapter6.example5;

import java.util.concurrent.*;

public class Worker implements Callable<Long> {

    @Override
    public Long call() throws Exception {
        long start = System.currentTimeMillis();
        int rand = (int) (Math.random() * Integer.MAX_VALUE);
        for(int i  = 0, sum = 0; i < rand; i++){
            sum+=1;
        }
        long end = System.currentTimeMillis();
        return (end - start);
    }

    public static void main(String[] args){
        int taskNum = 8;
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<Long> service = new ExecutorCompletionService<Long>(executorService);
        for(int i = 0; i < taskNum; i++){
            service.submit(new Worker());
        }
        Future<Long> future = null;
        Long timeTotal = 0L;
        for(int i = 0; i < taskNum; i++){
            try{
                future = service.take();
                timeTotal+=future.get();
            }catch (Exception e){
                e.printStackTrace();;
            }
        }
        System.out.println(timeTotal);
        executorService.shutdown();
    }
}
