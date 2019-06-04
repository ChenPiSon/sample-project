package com.yulintu.business.cache.service;

public interface RedisService {

    boolean add(String key,String value);

    boolean del(String key);
}
