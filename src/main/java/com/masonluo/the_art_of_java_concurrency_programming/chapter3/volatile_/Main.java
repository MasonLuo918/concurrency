package com.masonluo.the_art_of_java_concurrency_programming.chapter3.volatile_;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Thread thread1 = new Thread(worker);
        Thread thread2 = new Thread(worker);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
