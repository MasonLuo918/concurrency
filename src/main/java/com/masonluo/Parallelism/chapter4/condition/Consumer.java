package com.masonluo.Parallelism.chapter4.condition;


import com.masonluo.Parallelism.chapter4.multi_producer_consumer.CubbyHole;

public class Consumer extends Thread{
    private CubbyHole cubbyHole;

    private int id;

    public Consumer(CubbyHole c, int id){
        this.cubbyHole = c;
        this.id = id;
    }

    @Override
    public void run(){
        for(int i = 0; i < 50; i++){
            cubbyHole.get(id);
        }
    }
}
