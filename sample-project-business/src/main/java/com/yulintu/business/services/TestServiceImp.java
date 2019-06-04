package com.yulintu.business.services;

import com.google.common.base.Strings;
import com.yulintu.business.activiti.services.IProcessDeploymentService;
import com.yulintu.business.repositories.TestRepository;
import com.yulintu.business.entities.Jt;
import com.yulintu.thematic.data.DataException;
import com.yulintu.thematic.data.ServiceImpl;
import com.yulintu.thematic.data.models.Page;
import com.yulintu.thematic.data.models.PagingQuery;
import com.yulintu.thematic.data.validation.BeanValidatorUtils;
import com.yulintu.thematic.data.validation.Insert;
import com.yulintu.thematic.data.validation.Update;
import com.yulintu.thematic.web.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TestServiceImp extends ServiceImpl implements TestService {

    private int index;

    private TestRepository repository() {
        return getRepository(TestRepository.class);
    }

    @Autowired
    private Validator validator;

    @Autowired
    private IProcessDeploymentService processDeploymentService;


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
        index++;
        BeanValidatorUtils.validateWithException(validator, value, Insert.class);
        if (Strings.isNullOrEmpty(value.getId()))
            value.setId(UUID.randomUUID().toString());

        processDeploymentService.deleteDeployment("1");

        throw new RuntimeException("aaa");
//        int result=repository().add(value);
//
////        throw new ApiException();
//        return  result;
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
