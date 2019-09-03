package com.masonluo.Parallelism.chapter7.example2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MaxTask extends RecursiveTask<Integer> {

    private final long serialVersionUID = 1L;

    private Integer[] bigData;

    private int start;

    private int end;

    private int threshold;

    private int nthreads;

    public MaxTask(Integer[] bigData, int start, int end, int threshold, int nthreads) {
        this.bigData = bigData;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
        this.nthreads = nthreads;
    }

    @Override
    protected Integer compute() {
        Integer result;
        if(end - start < threshold){
            result = computeSequential();
        }else{
            System.out.println("类MaxTask：开始 Fork操作...");
            int scale = (end - start + nthreads - 1) / nthreads;
            System.out.println("类MaxTask：每段数据的长度为" + scale);
            int[] len = new int[nthreads + 1];
            for(int i = 0; i < nthreads; i++){
                len[i] = scale * i;
                if(len[i] > end){
                    len[i] = end;
                }
            }
            MaxTask[] tasks = new MaxTask[nthreads];
            System.out.printf("类maxTask:%d个任务被创建\n", nthreads);
            for(int i = 0; i < nthreads; i++){
                tasks[i] = new MaxTask(bigData, len[i], len[i + 1], threshold, nthreads);
            }
            invokeAll(tasks);
            List<Integer> results = new ArrayList<>();
            try{
                for(int i = 0; i < nthreads; i++){
                    Integer re = tasks[i].get();
                    System.out.printf("类MaxTask：任务%d的结果是%d\n",i, re);
                    results.add(re);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            result = max(results);
        }
        return result;
    }

    private Integer computeSequential(){
        Integer max = bigData[start];
        for(int i = start + 1; i < end; i++){
            if(max < bigData[i]){
                max = bigData[i];
            }
        }
        return max;
    }

    private Integer max(List<Integer> results){
        Integer result = 0;
        for(Integer integer:results){
            if(result < integer){
                result = integer;
            }
        }
        return result;
    }
}
