package com.yulintu.business.cache.repositories;

import com.yulintu.thematic.data.Repository;

import java.util.List;
import java.util.Map;

public interface MemoryRepository extends Repository {

    boolean putTableData(String key, Map<String, Object>table);

    Map<String, Object> getTableData(String key);
}
