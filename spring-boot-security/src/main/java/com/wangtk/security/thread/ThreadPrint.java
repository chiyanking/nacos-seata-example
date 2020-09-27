package com.wangtk.security.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPrint {
    public static void main(String[] args) throws InterruptedException {
//        int threadNum = 3;
//        int printNum = 20;
//        Semaphore[] semaphores = new Semaphore[threadNum];
//        CyclicBarrier barrier = new CyclicBarrier(threadNum);
//        for (int i = 0; i < threadNum; i++) {
//            new Thread(new PrintClass1(i, printNum, threadNum, semaphores, barrier)).start();
//        }
//        synchronized (semaphores) {
//            semaphores.wait();
//        }


        int max = 20;
        int threadNum = 3;
        Object object = new Object();
        for (int i = 0; i < threadNum; i++) {
            new Thread(new PrintClass2(max, threadNum, i, object)).start();
        }

        Object masterObject = new Object();
        synchronized (masterObject) {
            masterObject.wait();
        }
    }
}

class PrintClass1 implements Runnable {
    private int num;
    private int max;
    private int threadNum;
    private Semaphore semaphore[];
    private CyclicBarrier barrier;

    public PrintClass1(int num, int max, int threadNum, Semaphore[] semaphore, CyclicBarrier barrier) {
        this.num = num;
        this.max = max;
        this.threadNum = threadNum;
        this.semaphore = semaphore;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            semaphore[num] = new Semaphore(num == 0 ? 1 : 0);
            System.out.println("线程" + num + " 初始化完成");
            barrier.await();
            for (int j = 0; j < max; j++) {
                if (j % threadNum == num) {
                    semaphore[num].acquire();
                    System.out.println("线程 " + num + " print " + j);
                    semaphore[num + 1 == threadNum ? 0 : num + 1].release();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class PrintClass2 implements Runnable {


    private int max;
    private Integer allThread;
    private int thread;
    private Object lock;

    private static volatile AtomicInteger count = new AtomicInteger(1);

    public PrintClass2(int max, Integer allThread, int thread, Object lock) {
        this.max = max;
        this.allThread = allThread;
        this.thread = thread;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            while (true) {
                while (count.get() % allThread != thread) {
                    if (count.get() > max) {
                        return;
                    }
                    synchronized (lock) {
                        lock.wait();
                    }
                }
                System.out.println("thread " + thread + " print " + count.getAndIncrement());
                synchronized (lock) {
                    lock.notifyAll();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}