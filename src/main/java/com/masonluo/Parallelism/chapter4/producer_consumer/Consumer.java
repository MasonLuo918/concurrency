package com.masonluo.Parallelism.chapter4.producer_consumer;

public class Consumer extends Thread{
    private CubbyHole cubbyHole;

    public Consumer(CubbyHole c){
        this.cubbyHole = c;
    }

    @Override
    public void run(){
        for(int i = 0; i < 50; i++){
            cubbyHole.get();
        }
    }
}
