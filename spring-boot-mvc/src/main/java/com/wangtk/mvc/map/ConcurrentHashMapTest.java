package com.wangtk.mvc.map;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {

        ConcurrentHashMap<HashConflict, Integer> map = new ConcurrentHashMap<>(2);
        for (int i = 0; i < 1000000; i++) {
            map.put(new HashConflict(i), i);
        }

    }
}

class HashConflict {
    private Integer value;

    public HashConflict(Integer value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return value % 32;
    }

    @Override
    public boolean equals(Object obj) {
        return value.equals(obj);
    }
}
