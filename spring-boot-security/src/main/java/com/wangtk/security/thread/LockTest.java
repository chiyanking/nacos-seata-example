package com.wangtk.security.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);


        Thread thread1 = new Thread(() -> {

            lock.lock();
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
            }


        });

        thread1.setName("thread1");


        Thread thread2 = new Thread(() -> {

            lock.lock();
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
            }

        });

        thread1.setName("thread2");
        thread1.start();
//        Thread.sleep(100000);
        thread2.start();


    }
}
