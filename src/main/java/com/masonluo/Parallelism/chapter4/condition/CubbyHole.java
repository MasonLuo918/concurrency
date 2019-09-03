package com.masonluo.Parallelism.chapter4.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CubbyHole {
    private int[] goods;

    private int rear;

    private int front;

    private int count;

    private int nbuf;

    Lock lock = new ReentrantLock();

    Condition full = lock.newCondition();

    Condition empty = lock.newCondition();

    public CubbyHole(int nbuf){
        front = 0;
        rear = 0;
        this.nbuf = nbuf;
        goods = new int[nbuf];
        count = 0;
    }

    public int get(int id){
        lock.lock();
        try{
            while(count <= 0){
                try{
                    empty.await(); // 会自动释放锁，直到其他线程使用了singal就会重新获取
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            front = (front + 1) % nbuf;
            System.out.println("第" + id + "号消费者拿走了物品" + goods);
            count--;
            full.signal();
            return goods[front];
        }finally {
            lock.unlock();
        }
    }

    public void put(int value, int id){
        lock.lock();
        try{
            while(count >= nbuf){
                try {
                    full.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            rear = (rear + 1) % nbuf;
            goods[rear] = value;
            System.out.println("第" + id + "号生产者生产了物品" + value);
            count++;
            empty.signal();
        }finally {
            lock.unlock();
        }
    }

}
