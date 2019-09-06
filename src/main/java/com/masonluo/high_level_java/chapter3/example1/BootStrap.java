package com.masonluo.high_level_java.chapter3.example1;

public class BootStrap {
    public static final int a = 1;

    public static int b = 2;

    static {
        System.out.println("静态域加载");
    }

    public static int getB(){
        return b;
    }

    public static void main(String[] args) {
        System.out.println(BootStrap.a);
    }
}
