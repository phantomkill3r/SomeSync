package com.example.demo.service.cache;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemCache {
    Map<String,Object> cache;

    public MemCache(){
        cache = new HashMap<>();
    }

    public void store(String key, Object val){
        cache.put(key, val);
    }

    public void remove(String key){
        cache.remove(key);
    }

    public Object get(String key){
        return cache.get(key);
    }
}
