package com.masonluo.Parallelism.chapter4.multi_producer_consumer;

public class CubbyHole {
    private int[] goods;

    private int front;

    private int rear;

    private int count;

    private int nbuf;

    public CubbyHole(int nbuf){
        front = 0;
        rear = 0;
        this.nbuf = nbuf;
        goods = new int[nbuf];
        count = 0;
    }

    public synchronized int get(int id){
        while(count <= 0){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        front = (front + 1) % nbuf;
        System.out.println("第" + id + "号消费者拿走了物品" + goods[front]);
        count--;
        notifyAll();
        return goods[front];
    }

    public synchronized void put(int value, int id){
        while(count >= nbuf){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        rear = (rear + 1) % nbuf;
        goods[rear] = value;
        System.out.println("第" + id + "号生产者生产了物品" + goods[rear]);
        count++;
        notifyAll();
    }
}
