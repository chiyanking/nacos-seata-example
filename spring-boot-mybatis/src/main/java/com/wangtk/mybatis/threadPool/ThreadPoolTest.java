package com.wangtk.mybatis.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {


    public static void main(String[] args) {

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);


        for (int i = 0; i < 100; i++) {
            boolean offer = arrayBlockingQueue.offer(i);
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 300, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
        threadPoolExecutor.submit(() -> {
            ThreadLocal<Integer> localName = new ThreadLocal();
            localName.set(100000);
            localName.get();
            System.out.println("中文地址");
            System.out.println("地址信息");
            return "a";
        });
    }

}
