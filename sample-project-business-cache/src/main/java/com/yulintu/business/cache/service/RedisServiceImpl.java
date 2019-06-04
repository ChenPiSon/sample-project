package com.yulintu.business.cache.service;

import com.yulintu.business.cache.repositories.RedisRepository;
import com.yulintu.thematic.data.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl extends ServiceImpl implements RedisService {


    RedisRepository repository() {
        return getRepository(RedisRepository.class,"memoryProvider");
    }

    @Override
    public boolean add(String key, String value) {
        return repository().add(key,value);
    }

    @Override
    public boolean del(String key) {
        return false;
    }
}
