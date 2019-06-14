package com.yulintu.business.services;

import com.yulintu.business.activiti.services.IProcessDeploymentService;
import com.yulintu.business.repositories.TestRepository;
import com.yulintu.business.entities.Jt;
import com.yulintu.thematic.data.DataException;
import com.yulintu.thematic.data.ServiceImpl;
import com.yulintu.thematic.data.models.Page;
import com.yulintu.thematic.data.models.PagingQuery;
import com.yulintu.thematic.data.validation.BeanValidatorUtils;
import com.yulintu.thematic.data.validation.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.List;

@Service
@Transactional
public class TestServiceImp extends ServiceImpl implements TestService {


    private TestRepository repository() {
        return getRepository(TestRepository.class);
    }

    @Autowired
    private Validator validator;



    public Jt get(String bh) {
        return repository().get(bh);
    }

    public Page<Jt> paging(PagingQuery paging) {

        TestRepository repository = repository();
        long total = repository.count();
        List<Jt> items = repository.paging(paging);

        return new Page<>(items, total, paging.getSize(), paging.getPage());
    }

    @Transactional
    public int add(Jt value) {
        return repository().add(value);
    }

    @Transactional
    public int update(Jt value) {

        BeanValidatorUtils.validateWithException(validator, value, Update.class);

        if (repository().anyBhExceptById(value.getId(), value.getBh()))
            throw new DataException(String.format("编号 %s 已经存在。", value.getBh()));
        return repository().update(value);
    }

    @Transactional
    public int deleteAll() {
        return repository().deleteAll();
    }
}
