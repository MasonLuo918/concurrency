package com.masonluo.Parallelism.chapater2.interrupt;

public class LeapYearPrinter extends Thread {
    @Override
    public void run(){
        int year = 2000;
        System.out.println("闰年包括：");
        while(true){
            if(year % 4 == 0 && year % 100 !=0 || year % 400 == 0){
                System.out.println(year);
            }
            if(isInterrupted()){
                System.out.println("线程" + getName() + "已经被中断");
                return;
            }
            year++;
        }
    }
    public static void main(String[] args){
        Thread thread = new LeapYearPrinter();
        thread.start();
        try {
            Thread.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
