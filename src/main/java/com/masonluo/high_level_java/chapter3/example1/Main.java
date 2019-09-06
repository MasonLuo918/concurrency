package com.masonluo.high_level_java.chapter3.example1;

public class Main {
    public static void main(String[] args) {
//        System.out.println(BootStrap.a);
////        System.out.println(BootStrap.b);
//        System.out.println(BootStrap.getB());
        System.out.println(Main.class.getClassLoader().getParent());
    }
}
