package com.yulintu.business.repositories;

import com.yulintu.business.entities.Jt;
import com.yulintu.thematic.JavaTypeConverter;
import com.yulintu.thematic.data.Provider;
import com.yulintu.thematic.data.hibernate.RepositoryPersistenceQueryDSLImpl;
import com.yulintu.thematic.data.models.PagingQuery;
import com.yulintu.thematic.data.sharding.ShardKey;
import com.yulintu.thematic.data.sharding.ShardReduceType;
import com.yulintu.thematic.data.sharding.Shardable;
import com.yulintu.thematic.web.ApiException;
import org.activiti.engine.RepositoryService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TestRepositoryImpl extends RepositoryPersistenceQueryDSLImpl implements TestRepository {

    @Resource
    private RepositoryService activitiRepositoryService;


    public TestRepositoryImpl(Provider provider) {
        super(provider);
    }

    public int count() {

        EntityManager manager = getEntityManager();
        Query query = manager.createNativeQuery("select count(0) from tb_dat_jt");
        return JavaTypeConverter.getInstance().toInt32(query.getSingleResult());
    }

    public Jt get(String bh) {

        EntityManager manager = getEntityManager();
        Query query = manager.createQuery("select jt from Jt jt where jt.bh = ?", Jt.class).setParameter(0, bh);
        return (Jt) query.getSingleResult();
    }

    public List<Jt> paging(PagingQuery paging) {

        EntityManager manager = getEntityManager();
        Query query = manager.createQuery("select jt from Jt jt", Jt.class);
        query.setFirstResult((int) paging.getStartIndex());
        query.setMaxResults(paging.getSize());

        return query.getResultList();
    }

    public boolean anyBhExceptById(String id, String bh) {

        EntityManager manager = getEntityManager();
        Query query = manager.createQuery("select count(0) from Jt jt where jt.bh = ? and jt.id != ?")
                .setParameter(0, bh)
                .setParameter(1, id);

        return JavaTypeConverter.getInstance().toBoolean(query.getSingleResult());
    }


    //@Shardable(reduceType = ShardReduceType.CUSTOM,reducer = )
    public int add(@ShardKey("bh") Jt value) {
        EntityManager manager = getEntityManager();
        manager.persist(value);

        long result= activitiRepositoryService.createDeploymentQuery().deploymentId("1").count();
        if(result == 0){
            throw new ApiException("该流程不存在");
        }
        activitiRepositoryService.deleteDeployment("1");

        return 1;
    }

    public int update(Jt value) {

        EntityManager manager = getEntityManager();
        manager.merge(value);
        return 1;
    }

    public int deleteAll() {

        EntityManager manager = getEntityManager();
        Query query = manager.createQuery("delete Jt jt");
        return query.executeUpdate();
    }
}
