package com.wangtk.mybatis.config;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class LocalCache implements Cache {

    @Autowired
    private Map<Object, Object> store = new HashMap<>();

    String id;

    public LocalCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;

    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("put key " + JSON.toJSONString(key) + " value" + JSON.toJSONString(value));
        store.put(key, value);
    }

    @Override
    public Object getObject(Object key) {
        System.out.println("get " + JSON.toJSONString(key));
        return store.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        System.out.println("remove " + JSON.toJSONString(key));
        return store.remove(key);
    }

    @Override
    public void clear() {
        System.out.println(" clear ");
        store = new HashMap<>();
    }

    @Override
    public int getSize() {
        return store.size();
    }
}
