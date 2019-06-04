package com.yulintu.business.repositories;

import com.yulintu.thematic.data.Provider;
import com.yulintu.thematic.data.ProviderType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository
@Scope("prototype")
@ProviderType("postgresql")
public class TestRepositoryImplPostgreSQL extends TestRepositoryImpl  {



    public TestRepositoryImplPostgreSQL(Provider provider) {
        super(provider);
    }
}
