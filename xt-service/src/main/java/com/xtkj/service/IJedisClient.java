package com.xtkj.service;

import com.xtkj.pojo.UserInfo;

import java.util.List;
import java.util.Set;

public interface IJedisClient {
    String get(String key);

    String set(String key, String value);

    long ttl(String key);

    long expire(String key, int second);

    long incr(String key);

    void hset(String hkey, String key, String value);

    String hget(String hkey, String key);

    long del(String key);

    void hdel(String hkey, String key);

    Set<String> keys(String k);

    List<UserInfo> hgetAll(String k);

}
