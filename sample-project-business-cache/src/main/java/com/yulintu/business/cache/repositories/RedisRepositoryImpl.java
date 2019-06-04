package com.yulintu.business.cache.repositories;

import com.yulintu.thematic.data.Provider;
import com.yulintu.thematic.data.ProviderType;
import com.yulintu.thematic.data.RepositoryImpl;
import com.yulintu.thematic.data.redis.ProviderRedis;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
@ProviderType("redis")
public class RedisRepositoryImpl extends RepositoryImpl implements RedisRepository {


    private ProviderRedis provider;


    public RedisRepositoryImpl(Provider provider) {
        super(provider);
        this.provider=(ProviderRedis)provider;
    }



    @Override
    public boolean add(String key, String value) {
        provider.executeInSession(jedis -> jedis.set(key, value));
        return true;
    }

    @Override
    public boolean del(String key) {
        provider.executeInSession(jedis -> jedis.del(key));
        return true;
    }
}
