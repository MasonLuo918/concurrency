package com.masonluo.Parallelism.chapter7.example1;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class UpdateTask extends RecursiveAction {

    private static final long serialVersionUID = 1L;

    private List<ClassInfo> classInfos;

    private int start;

    private int end;

    private int increment;

    private int nthreads;

    private int threshold;

    public UpdateTask(List<ClassInfo> classInfos, int start, int end, int increment, int nthreads, int threshold) {
        this.classInfos = classInfos;
        this.start = start;
        this.end = end;
        this.increment = increment;
        this.nthreads = nthreads;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if(end - start <= threshold || nthreads == 1){
            updateSequential();
        }else{
            UpdateTask[] tasks = new UpdateTask[nthreads];
            int[] data = new int[nthreads + 1];
            int segment = (end - start + nthreads - 1) / nthreads;
            for(int i = 0; i <= nthreads; i++){
                data[i] = start + segment * i;
                if(data[i] > end){
                    data[i] = end;
                }
            }
            int mid = (end - start) / 2;
            for(int i = 0; i < nthreads; i++){
                tasks[i] = new UpdateTask(classInfos, data[i], data[i + 1], increment, nthreads, threshold);
            }
            // 在Fork/Join框架内部进行提交
            invokeAll(tasks);
        }
    }

    private void updateSequential(){
        for(int i = start; i < end; i++){
            ClassInfo classInfo = classInfos.get(i);
            classInfo.setNumber(classInfo.getNumber() + increment);
        }
    }
}
