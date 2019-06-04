package com.yulintu.business.cache.service;

import java.util.List;
import java.util.Map;

public interface MemoryService {

  void putTableData(String key, Map<String, Object> table);

  Map<String,Object> getTableData(String key);

}
