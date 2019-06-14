package com.yulintu.business.repositories;

import com.sun.tracing.ProviderName;
import com.yulintu.business.entities.Jt;
import com.yulintu.thematic.data.Provider;
import com.yulintu.thematic.data.ProviderDb;
import com.yulintu.thematic.data.ProviderType;
import com.yulintu.thematic.data.RepositoryFactory;
import com.yulintu.thematic.data.sharding.ProviderSharding;
import com.yulintu.thematic.data.sharding.RepositorySharding;
import com.yulintu.thematic.data.sharding.ShardConfig;
import com.yulintu.thematic.data.sharding.ShardKey;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@Scope("prototype")
@ProviderType("sharding")
@ShardConfig("zone")
public class TestRepositoryImplSharding extends TestRepositoryImpl implements RepositorySharding {

    public TestRepositoryImplSharding(Provider provider) {
        super(provider);
    }

    @Override
    public List<ProviderDb> getCurrentShards() {
        return ((ProviderSharding) getProvider()).getCurrentShards();
    }


    @Override
    public int add(@ShardKey("bh") Jt value) {

        long sum = getCurrentShards().stream().mapToLong(c ->
                RepositoryFactory.get(c).create(TestRepository.class, true).add(value)).sum();

        return (int) sum;
    }
}
