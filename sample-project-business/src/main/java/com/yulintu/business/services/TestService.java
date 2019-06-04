package com.yulintu.business.services;

import com.yulintu.business.entities.Jt;
import com.yulintu.thematic.data.Service;
import com.yulintu.thematic.data.models.Page;
import com.yulintu.thematic.data.models.PagingQuery;

public interface  TestService extends Service {

    Jt get(String bh);

    Page<Jt> paging(PagingQuery paging);

    int add(Jt value);

    int update(Jt value);

    int deleteAll();
}
