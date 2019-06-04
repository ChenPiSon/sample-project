package com.yulintu.business.controllers;


import com.yulintu.business.cache.service.MemoryService;
import com.yulintu.business.cache.service.RedisService;
import com.yulintu.business.services.TestService;
import com.yulintu.business.entities.Jt;
import com.yulintu.thematic.data.models.Page;
import com.yulintu.thematic.data.models.PagingQuery;
import com.yulintu.thematic.web.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Api(tags = {"示例Controller"})
public class TestController {

    private  int index=0;
    @Autowired
    private TestService testService;

    @Autowired
    private RedisService cacheService;

    @Autowired
    private MemoryService memoryService;

    @ApiOperation(value = "获取指定编号数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bh", value = "编号", required = true, dataType = "String")})
    @ResponseData
    @GetMapping("/business/{bh}")
    public Jt get(@PathVariable String bh) {

        Map<String,Object>objectMap =new HashMap<>();
        if(index==0){
            objectMap.put("1",0);
            objectMap.put("2","test");
            index=1;
        }
        else {
            objectMap.put("3",0);
            objectMap.put("4","test");
        }

        memoryService.putTableData("desc",objectMap);
        return null;
    }

    @ApiOperation(value = "分页获取")
    @ApiImplicitParams({})
    @ResponseData
    @GetMapping("/business")
    public Page<Jt> paging(@ModelAttribute PagingQuery paging) {
        return testService.paging(paging);
    }

    @ApiOperation(value = "添加一个新的记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "value", value = "记录", required = true, dataType = "Jt")})
    @ResponseData
    @PutMapping("/")
    public int add(@RequestBody Jt value) {
        return testService.add(value);
    }

    @ApiOperation(value = "更新指定记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = " Id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "value", value = "数值", required = true, dataType = "Jt")})
    @ResponseData
    @PostMapping("/")
    public int update(String id, @RequestBody Jt value) {
        value.setId(id);
        return testService.update(value);
    }

    @ApiOperation(value = "删除所有记录")
    @ApiImplicitParams({})
    @ResponseData
    @DeleteMapping("/business")
    public int deleteAll() {
        return testService.deleteAll();
    }



}
