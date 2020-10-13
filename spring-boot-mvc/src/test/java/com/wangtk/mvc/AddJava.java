package com.wangtk.mvc;

public class AddJava {


    public static volatile int num = 11;

    public static void main(String[] args) throws InterruptedException {

        Object monitor = new Object();

        Thread thread1 = new Thread(() -> {
            try {
                while (true) {
                    synchronized (monitor) {
                        int value = num - 3;
                        if (value < 0) {
                            num = 0;
                        } else {
                            num = value;
                        }
                        System.out.println(num);
                        if (num == 0) {
                            monitor.wait();
                        }
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });


        Thread thread2 = new Thread(() -> {

            while (true) {
                try {
                    synchronized (monitor) {
                        int value = num - 4;
                        if (value <= 0) {
                            num = 0;
                        } else {
                            num = value;
                        }
                        if (num == 0) {
                            monitor.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });


        thread1.start();
        thread2.start();

        Object object = new Object();
        synchronized (object) {
            object.wait();
        }
    }

}
