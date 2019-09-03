package com.masonluo.Parallelism.chapter6.example4;

import java.util.concurrent.*;

public class Monitor implements Runnable {

    Machine machine;

    ScheduledExecutorService scheduler;

    static int n = 0;

    Monitor(Machine machine, ScheduledExecutorService scheduler){
        this.machine = machine;
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        if(machine.getTemperature() >= 10){
            System.out.println("警告：机器温度过高");
            n++;
        }
        if(n > 10){
            System.out.println("提醒次数限制已到，终止任务");
            scheduler.shutdown();
        }
    }

    public static void main(String[] args){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        Machine machine = new Machine(0);
        Monitor monitor = new Monitor(machine, scheduledExecutorService);
        scheduledExecutorService.scheduleAtFixedRate(machine, 1, 2, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(monitor, 0, 1, TimeUnit.SECONDS);
    }
}
