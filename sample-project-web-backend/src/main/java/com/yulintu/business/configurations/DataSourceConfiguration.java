package com.yulintu.business.configurations;
import com.yulintu.thematic.data.*;
import com.yulintu.thematic.data.hibernate.EntityManagerFactoryPool;
import com.yulintu.thematic.data.hibernate.HibernateConnectionStringBuilder;
import com.yulintu.thematic.data.redis.RedisConnectionStringBuilder;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;


@Configuration
public class DataSourceConfiguration {

    @Bean(name = "dataSourceSingle")
    public DataSource dataSource() {

        ClassPathXmlApplicationContext ac = ClassPathXmlApplicationContextPool.findInitialize("datasources/spring.datasource.xml");
        DataSource ds1 = ac.getBean(DataSource.class);

        return ds1;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        return new ThreadAwareTransactionManager();
    }

    @Bean
    @Scope("prototype")
    public Provider provider1() {
        HibernateConnectionStringBuilder builder = new HibernateConnectionStringBuilder();
        builder.setConfigureFilePath("datasources/spring.datasource.xml");
        builder.setConfigureFileType("spring");
        String connectionString = builder.getConnectionString();
        EntityManagerFactoryPool.initialize(connectionString);
        String providerType = EntityManagerFactoryPool.getProviderType(connectionString);
        return ProviderUtils.create(providerType, connectionString);
    }

    @Bean
    @Scope("prototype")
    public Provider provider2() {
        HibernateConnectionStringBuilder builder = new HibernateConnectionStringBuilder();
        builder.setConfigureFilePath("datasources/spring.datasource.querydsl.xml");
        builder.setConfigureFileType("spring");
        String connectionString = builder.getConnectionString();
        EntityManagerFactoryPool.initialize(connectionString);
        String providerType = EntityManagerFactoryPool.getProviderType(connectionString);
        return ProviderUtils.create(providerType, connectionString);
    }



    @Bean
    @Primary
    @Scope("prototype")
    public Provider provider3() {
        FileConnectionStringBuilder builder = new FileConnectionStringBuilder();
        builder.setFilePath("datasources/spring.datasource.shards.xml");
        return ProviderUtils.create("sharding", builder.getConnectionString());
    }


    @Bean
    @Scope("prototype")
    public Provider redisProvider() {
        RedisConnectionStringBuilder builder=new RedisConnectionStringBuilder("configure=datasources/spring.datasource.redis.xml");
        Provider providerRedis =   ProviderUtils.create("redis", builder.getConnectionString());
        return providerRedis;
    }


    @Bean
    @Lazy
    public Provider memoryProvider() {
        Provider memoryProvider =   ProviderUtils.create("memorydb", null);
        return memoryProvider;
    }
}
