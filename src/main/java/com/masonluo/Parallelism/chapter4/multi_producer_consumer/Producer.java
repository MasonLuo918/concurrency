package com.masonluo.Parallelism.chapter4.multi_producer_consumer;


public class Producer extends Thread {
    private CubbyHole cubbyHole;

    private int id;

    public Producer(CubbyHole c, int id){
        cubbyHole = c;
        this.id = id;
    }

    @Override
    public void run(){
        for(int i = 0; i < 50; i++){
            cubbyHole.put((int)(100 * Math.random()), id);
        }
    }
}
