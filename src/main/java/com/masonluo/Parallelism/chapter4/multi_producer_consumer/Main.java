package com.masonluo.Parallelism.chapter4.multi_producer_consumer;

public class Main {
    private static int np = 4;

    private static int nc = 4;

    public static int nbuf = 10;

    public static void main(String[] args){
        CubbyHole cubbyHole = new CubbyHole(nbuf);
        Producer[] producers = new Producer[np];
        Consumer[] consumers = new Consumer[nc];
        for(int i = 0; i < np; i++){
            producers[i] = new Producer(cubbyHole, i + 1);
            producers[i].start();
        }

        for(int i = 0; i < nc; i++){
            consumers[i] = new Consumer(cubbyHole, i + 1);
            consumers[i].start();
        }

        try{
            for(int i = 0; i < np; i++){
                producers[i].join();
            }

            for(int i = 0; i < np; i++){
                consumers[i].join();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}