package com.yulintu.business.redis;

import com.yulintu.thematic.data.redis.ProviderRedis;
import com.yulintu.thematic.data.redis.ProviderRedisImpl;
import com.yulintu.thematic.data.redis.RedisConnectionStringBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestRedis {


    @Test
    public void init() {

        System.out.println("开始测试-----------------");

        RedisConnectionStringBuilder builder=new RedisConnectionStringBuilder("configure=datasources/spring.datasource.redis.xml");
        ProviderRedis provider0 = new ProviderRedisImpl(builder.getConnectionString());

        System.out.println(provider0.isConnectionOpened());

        provider0.executeInSession(jedis -> System.out.println(jedis.get("des")));

        provider0.executeInSession(jedis -> jedis.set("des", "value from 0"));
        provider0.executeInSession(jedis -> jedis.set("des", "value from 1"));

        provider0.executeInSession(jedis -> System.out.println(jedis.get("des")));

        provider0.executeInSession(jedis -> jedis.del("des"));
        provider0.executeInSession(jedis -> System.out.println(jedis.get("des")));

        System.out.println("结束测试-----------------");

    }

}
