package com.yulintu.business.cache.repositories;

import com.yulintu.thematic.data.Repository;

public interface RedisRepository extends Repository {

    boolean add(String key,String value);

    boolean del(String key);
}
