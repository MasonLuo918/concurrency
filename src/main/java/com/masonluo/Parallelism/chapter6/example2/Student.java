package com.masonluo.Parallelism.chapter6.example2;

import javax.annotation.security.RunAs;

public class Student implements Runnable {

    String name;

    Student(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "开始考试");
        long start = System.currentTimeMillis();
        int num = (int)(Math.random() * Integer.MAX_VALUE);
        for(int i = 0; i < num; i++);
        long end = System.currentTimeMillis();
        System.out.println(name + "考试完毕,答题用时:" + (end - start) + "毫秒");
    }

    public static void main(String[] args){
        int stuNum = 10;
        TestRoom testRoom = new TestRoom();
        for(int i = 0; i < stuNum; i++){
            Student student = new Student("考生" + (i + 1));
            testRoom.test(student);
        }
        testRoom.endTest();
    }
}
