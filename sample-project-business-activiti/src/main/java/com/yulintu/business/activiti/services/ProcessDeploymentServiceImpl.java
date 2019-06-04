package com.yulintu.business.activiti.services;//package com.yulintu.business.activiti.services;

import com.yulintu.business.activiti.services.IProcessDeploymentService;
import com.yulintu.thematic.data.ServiceImpl;
import com.yulintu.thematic.web.ApiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class ProcessDeploymentServiceImpl extends ServiceImpl implements IProcessDeploymentService {


    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TaskService taskService;


    @Override
    public boolean deleteDeployment(String deploymentId) {

//       long result= repositoryService.createDeploymentQuery().deploymentId(deploymentId).count();
//        if(result == 0){
//            throw new ApiException("该流程不存在");
//        }

        repositoryService.deleteDeployment(deploymentId);
        return  true;
    }
}
