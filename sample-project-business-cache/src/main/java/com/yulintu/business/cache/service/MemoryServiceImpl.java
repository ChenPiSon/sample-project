package com.yulintu.business.cache.service;

import com.yulintu.business.cache.repositories.MemoryRepository;
import com.yulintu.thematic.data.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemoryServiceImpl extends ServiceImpl implements MemoryService {

    MemoryRepository repository() {
        return getRepository(MemoryRepository.class,"memoryProvider");
    }

    @Override
    public void putTableData(String key, Map<String, Object> table) {
        repository().putTableData(key, table);
    }

    @Override
    public Map<String, Object> getTableData(String key) {
        return repository().getTableData(key);
    }
}
