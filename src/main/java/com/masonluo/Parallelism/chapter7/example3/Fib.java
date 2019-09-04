package com.masonluo.Parallelism.chapter7.example3;

import java.util.concurrent.RecursiveTask;

public class Fib extends RecursiveTask<Integer> {

    Integer num;

    public Fib(Integer num){
        this.num = num;
    }

    @Override
    protected Integer compute() {
        Integer result;
        if(num <= 10){
//            result = getValue(num);
            return 0;
        }else{
            Fib fibTask1 = new Fib(num - 1);
            Fib fibTask2 = new Fib(num - 2);
            fibTask1.fork();
            fibTask2.fork();
            result = fibTask1.join() + fibTask2.join();
        }
        return result;
    }
}
