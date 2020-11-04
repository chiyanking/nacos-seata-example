package cn.wangtk.thread;

public class ThreadBlock {
    public static void main(String[] args) {
        Object lock = new Object();
        for (int i = 0; i < 20; i++) {

            new Thread(() -> {
                synchronized (lock) {
                    try {
                        Thread.sleep(200000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
