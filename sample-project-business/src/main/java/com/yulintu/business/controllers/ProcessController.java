package com.yulintu.business.controllers;


import com.yulintu.business.activiti.services.IProcessDeploymentService;
import com.yulintu.thematic.web.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags ={"流程Controller示例"})
public class ProcessController {

    @Autowired
    private IProcessDeploymentService processDeploymentService;

    @ApiOperation(value = "deleteDeployment")
    @ApiImplicitParams({@ApiImplicitParam(name = "deploymentId", value = "deploymentId", required = true, dataType = "String")})
    @GetMapping(value = "/deleteDeployment{deploymentId}")
    @ResponseData
    public boolean deleteDeployment(@PathVariable String deploymentId) {
        return processDeploymentService.deleteDeployment(deploymentId);
    }

}
