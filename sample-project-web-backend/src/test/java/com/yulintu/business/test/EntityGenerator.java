package com.yulintu.business.test;

import com.querydsl.codegen.BeanSerializer;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.OracleTemplates;
import com.querydsl.sql.codegen.MetaDataExporter;
import com.yulintu.thematic.data.hibernate.ProviderPersistence;
import com.yulintu.thematic.data.hibernate.ProviderPersistenceImpl;
import com.yulintu.thematic.data.redis.ProviderRedis;
import com.yulintu.thematic.data.redis.ProviderRedisImpl;
import com.yulintu.thematic.data.redis.RedisConnectionStringBuilder;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static com.yulintu.thematic.data.hibernate.ProviderPersistenceImpl.*;

public class EntityGenerator {

    public static void main(String[] args) {

//        RedisConnectionStringBuilder builder=new RedisConnectionStringBuilder("configure=datasources/spring.datasource.redis.xml");
//        ProviderRedis provider0 = new ProviderRedisImpl(builder.getConnectionString());


        ProviderPersistence provider = createByFileName("datasources/spring.datasource.oracle.xml");

        provider.allInSession(null, (em, jpa, sql) -> {

            Session session = em.unwrap(Session.class);
            session.doWork(c -> {

                MetaDataExporter exporter = new MetaDataExporter();

                // 只生成 tb_ 开头的表
                exporter.setTableNamePattern("BDCS_%");
                exporter.setExportTables(true);

                // 生成 实体
                exporter.setBeanSerializer(new BeanSerializer());
                exporter.setBeanPackageName("com.yulintu.business.entities");
                exporter.setBeansTargetFolder(new File("target/generated-sources/java"));
                exporter.setLowerCase(true);

                // 生成 查询对象
                exporter.setNamePrefix("Q");
                exporter.setPackageName("com.yulintu.business.entities");
                exporter.setTargetFolder(new File("target/generated-sources/java"));

                // 开始生成
                exporter.export(c.getMetaData());
            });

            return null;
        });

    }



}