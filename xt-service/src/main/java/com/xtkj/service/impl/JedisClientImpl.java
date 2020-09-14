package com.xtkj.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xtkj.pojo.UserInfo;
import com.xtkj.service.IJedisClient;
import com.xtkj.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.*;

@Component
public class JedisClientImpl implements IJedisClient {
    @Autowired
    private JedisPool pool;

    @Autowired
    private IUserInfoService iUserInfoService;

    @Override
    public String get(String key) {
        return pool.getResource().get(key);
    }

    @Override
    public String set(String key, String value) {
        return pool.getResource().set(key, value);
    }

    @Override
    public long ttl(String key) {
        return pool.getResource().ttl(key);
    }

    @Override
    public long expire(String key, int second) {
        return pool.getResource().expire(key, second);
    }

    @Override
    public long incr(String key) {
        return pool.getResource().incr(key);
    }

    @Override
    public void hset(String hkey, String key, String value) {
        new Thread(() -> {
            pool.getResource().hset(hkey, key, value);
        }).start();
    }

    @Override
    public String hget(String hkey, String key) {
        return pool.getResource().hget(hkey, key);
    }

    @Override
    public long del(String key) {
        return pool.getResource().del(key);
    }

    @Override
    public void hdel(String hkey, String key) {
        new Thread(() -> {
            pool.getResource().hdel(hkey, key);
        }).start();
    }

    @Override
    public Set<String> keys(String k) {
        return pool.getResource().keys(k);
    }

    @Override
    public List<UserInfo> hgetAll(String k) {
        List<UserInfo> list = new ArrayList<>();
        Gson gson = new Gson();
        Collection<String> values = pool.getResource().hgetAll(k).values();
        Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()) {
            JsonObject jsonObject = gson.fromJson(iterator.next(), JsonObject.class);
            UserInfo userInfo = gson.fromJson(jsonObject, UserInfo.class);
            list.add(userInfo);
        }
        if (list.isEmpty()) {
            list = iUserInfoService.list();
            System.out.println("该数据从数据库中获取，非redis");
        }
        return list;
    }
}
