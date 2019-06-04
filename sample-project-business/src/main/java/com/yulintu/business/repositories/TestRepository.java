package com.yulintu.business.repositories;

import com.yulintu.business.entities.Jt;
import com.yulintu.thematic.data.Repository;
import com.yulintu.thematic.data.models.PagingQuery;

import java.util.List;

public interface TestRepository extends Repository {

    int count();

    Jt get(String bh);

    List<Jt> paging(PagingQuery paging);

    boolean anyBhExceptById(String id, String bh);

    int add(Jt value);

    int update(Jt value);

    int deleteAll();
}
