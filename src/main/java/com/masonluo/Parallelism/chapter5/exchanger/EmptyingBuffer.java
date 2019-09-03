package com.masonluo.Parallelism.chapter5.exchanger;

import java.util.concurrent.Exchanger;

public class EmptyingBuffer extends Thread {
    DataBuffer buffer;

    Exchanger<DataBuffer> exchanger;

    public EmptyingBuffer(DataBuffer buffer, Exchanger<DataBuffer> exchanger){
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    public void run(){
        try{
            while(!buffer.isEmpty()){
                if(buffer.isFull()){
                    System.out.println(getName() + "的缓冲区已满，开始消费");
                }
                buffer.take();
                if(buffer.isEmpty()){
                    System.out.println(getName() + "的缓冲区已经空了，等待交换数据");
                    buffer = exchanger.exchange(buffer);
                    System.out.println(getName() + "交换数据成功");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
