package com.masonluo.the_art_of_java_concurrency_programming.chapter4.pipeline;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Piped {
    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        // 将两个流连接
        out.connect(in);
        Thread printThread = new Thread(new Print(in), "PrintThread");
        printThread.start();
        int receive = 0;
        try{
            while((receive = System.in.read()) != -1){
                out.write(receive);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            out.close();
        }
    }

    static class Print implements Runnable{
        private PipedReader in;
        public Print(PipedReader in){
            this.in = in;
        }

        @Override
        public void run(){
            int receive = 0;
            try{
                while((receive = in.read()) != -1){
                    System.out.println((char) receive);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
