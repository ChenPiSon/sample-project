package com.yulintu.business.repositories;

import com.yulintu.thematic.data.Provider;
import com.yulintu.thematic.data.ProviderDb;
import com.yulintu.thematic.data.ProviderType;
import com.yulintu.thematic.data.sharding.ProviderSharding;
import com.yulintu.thematic.data.sharding.RepositorySharding;
import com.yulintu.thematic.data.sharding.ShardConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Scope("prototype")
@ProviderType("sharding")
@ShardConfig("zoneCode")
public class TestRepositoryImplSharding extends  TestRepositoryImpl implements RepositorySharding {

    public TestRepositoryImplSharding(Provider provider){
        super(provider);
    }

    @Override
    public List<ProviderDb> getCurrentShards() {
        return ((ProviderSharding) getProvider()).getCurrentShards();
    }


}
