package com.masonluo.concurrency.example.publish;

import com.masonluo.concurrency.annotation.NotRecommend;
import com.masonluo.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape(){
        // 隐式启动一个线程
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass(){
            // 在Escape对象还没有创建完成，已经将其发布
            log.info("{}", Escape.this.thisCanBeEscape);
        }

    }

    public static void main(String[] args){
        new Escape();
    }
}
