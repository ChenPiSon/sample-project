package com.yulintu.business.cache.repositories;

import com.yulintu.thematic.data.Provider;
import com.yulintu.thematic.data.ProviderType;
import com.yulintu.thematic.data.RepositoryImpl;
import com.yulintu.thematic.data.memorydb.MemoryDb;
import com.yulintu.thematic.data.memorydb.ProviderMemoryDb;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
@Scope("prototype")
@ProviderType("memorydb")
public class MemoryRepositoryImpl extends RepositoryImpl implements MemoryRepository {

    private MemoryDb memoryDb;

    public MemoryRepositoryImpl(Provider provider) {
        super(provider);
        this.memoryDb=((ProviderMemoryDb)provider).getDb();
    }


    @Override
    public Map<String, Object> getTableData(String key) {
        return memoryDb.getTableData(key).get(0);
    }


    @Override
    public boolean putTableData(String key, Map<String, Object> table) {

        if(memoryDb==null)
            throw new NullPointerException();

        ConcurrentHashMap<String,Object> tableData=new ConcurrentHashMap<String,Object>();

        if(memoryDb.hasTable(key)){
            tableData=((ConcurrentHashMap<String,Object>) memoryDb.getTableData(key).get(0));
        }

        synchronized (tableData){
            tableData.putAll(table);
            List<Map<String, Object>> result= new ArrayList<>();
            result.add(tableData);
            memoryDb.putTableData(key,result);
        }

        return true;
    }
}
