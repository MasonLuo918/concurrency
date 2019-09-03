package com.masonluo.Parallelism.chapter4.producer_consumer;

public class CubbyHole {
    private int goods;

    private boolean empty;

    public CubbyHole(){
        empty = true;
    }

    public synchronized int get(){
        while(empty){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("消费者拿走了物品 " + goods);
        empty = true;
        notify();
        return goods;
    }

    public synchronized void put(int value){
        while(!empty){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        goods = value;
        System.out.println("生产者生产了物品 " + goods);
        empty = false;
        notify();
    }
}
