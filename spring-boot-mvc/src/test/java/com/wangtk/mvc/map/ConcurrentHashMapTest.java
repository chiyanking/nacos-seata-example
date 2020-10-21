package com.wangtk.mvc.map;

import org.junit.Test;

public class ConcurrentHashMapTest {

    class HashConflict {
        private Integer value;

        public HashConflict(Integer value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value % 4;
        }

        @Override
        public boolean equals(Object obj) {
            return value.equals(obj);
        }
    }

    @Test
    public void testConflict() {
        ConcurrentHashMap<HashConflict, Integer> map = new ConcurrentHashMap<>(2);
        for (int i = 0; i < 1000000; i++) {
            map.put(new HashConflict(i), i);
        }
    }

    @Test
    public void testMove() {


        System.out.println("左移运算符，num << 1,相当于num乘以2");
        int i = 16 >>> 2;
        int j = 16 >> 2;
        System.out.println(i);
        System.out.println(j);


        System.out.println(">>> 无符号右移，忽略符号位，空位都以0补齐");
        i = -2 >>> 4;
        System.out.println(i);
        System.out.println("右移运算符，num >> 1,相当于num除以2");
        j = -2 >> 4;
        System.out.println(j);


        int ib = 0b1111111111111111111111111111101;
        System.out.println(ib);
        System.out.println(Integer.toBinaryString(120));
//        -2
//原码        1000 0000 0000 0000 0000 0000 0000 0010
//取反        1111 1111 1111 1111 1111 1111 1111 1101
//加一        1111 1111 1111 1111 1111 1111 1111 1110

//        正数补数即为本身，负数A【补】=模-绝对值（A）
//        所谓反码，英语里又叫ones' complement（对1求补），这里的1，本质上是一个有限位计数系统里所能表示出的最大值，
//        在8位二进制里就是11111111，在1位十进制里就是9，在3位十六进制里就是FFF（再大就要进位了）。
//        求反又被称为对一求补，用最大数减去一个数就能得到它的反

//        111 1111 1111 1111 1111 1111 1111 1111
//        000 0000 0000 0000 0000 0000 0000 0010
//        111 1111 1111 1111 1111 1111 1111 1101
//        对于十进制 来说  -2 的 反码是 7
//        对于十进制 10 = 9+1 （9-2)+1

//        01111000
//        01111000
//        11110000

    }

    @Test
    public void testList() throws InterruptedException {
        java.util.concurrent.ConcurrentHashMap<HashConflict, Integer> hashMap = new java.util.concurrent.ConcurrentHashMap<>(2);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                hashMap.put(new HashConflict(i), i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                hashMap.put(new HashConflict(i), i);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(hashMap);
    }
}