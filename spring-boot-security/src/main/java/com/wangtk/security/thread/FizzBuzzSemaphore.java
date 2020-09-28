package com.wangtk.security.thread;

import java.util.function.IntConsumer;

public class FizzBuzzSemaphore {

    private int n;
    private volatile int curNum = 1;
    Object lock = new Object();

    public FizzBuzzSemaphore(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (curNum <= n) {
            if (curNum % 3 == 0 && curNum % 5 != 0) {
                synchronized (lock) {
                    printFizz.run();
                    curNum++;
                    notifyAll();
                }
                continue;
            }
            synchronized (lock) {
                lock.wait();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (curNum <= n) {
            if (curNum % 3 != 0 && curNum % 5 == 0) {
                synchronized (lock) {
                    printBuzz.run();
                    curNum++;
                    lock.notifyAll();
                }
                continue;
            }
            synchronized (lock) {
                lock.wait();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (curNum <= n) {
            if (curNum % 3 == 0 && curNum % 5 == 0) {

                continue;
            }
            synchronized (lock) {
                printFizzBuzz.run();
                curNum++;
                lock.notifyAll();
            }
            synchronized (lock) {
                lock.wait();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (curNum <= n) {
            if (curNum % 3 == 0 || curNum % 5 == 0) {
                synchronized (lock) {
                    lock.wait();
                }
                continue;
            }
            synchronized (lock) {
                System.out.println(curNum);
                curNum++;
                lock.notifyAll();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

        FizzBuzzSemaphore fizzBuzzSemaphore = new FizzBuzzSemaphore(600);

        new Thread(() -> {
            try {
                fizzBuzzSemaphore.fizz(() -> {
                    System.out.println("fizz");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzzSemaphore.buzz(() -> {
                    System.out.println("buzz");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzzSemaphore.fizzbuzz(() -> {
                    System.out.println("fizzbuzz");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzzSemaphore.number(value -> {
                    System.out.println(value);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Object lo = new Object();
        synchronized (lo) {
            lo.wait();
        }
    }
}
