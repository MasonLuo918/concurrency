package com.masonluo.Parallelism.chapter6.example3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestRoom {
    ExecutorService testPool;

    Map<Student, Future<Long>> sutToTimeMap;

    TestRoom(){
        testPool = Executors.newFixedThreadPool(4);
        sutToTimeMap = new HashMap<>();
    }

    public void test(Student student){
        Future<Long> result = testPool.submit(student);
        sutToTimeMap.put(student, result);
    }

    public Long getTime(Student student){
        Future<Long> result = sutToTimeMap.get(student);
        try{
            return result.get();
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
            return null;
        }
    }

    public void endTest(){
        if(!testPool.isShutdown()){
            testPool.shutdown();
        }
    }

}
