package cn.wangtk.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {


        final Object lock = new Object();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        System.out.println("线程休眠");
                        synchronized (lock) {
                            lock.wait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        thread.start();

        Thread.sleep(200L);
        System.out.println("打断线程");
        thread.interrupt();

        Object lockMaster = new Object();
        synchronized (lockMaster) {
            lockMaster.wait();
        }


    }
}
