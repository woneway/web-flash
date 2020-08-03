package com.mtsearch.operation.dao.system;


import com.mtsearch.operation.bean.entity.system.Dict;
import com.mtsearch.operation.dao.BaseRepository;

import java.util.List;

public interface DictRepository extends BaseRepository<Dict, Long> {
    List<Dict> findByPid(Long pid);

    List<Dict> findByNameAndPid(String name, Long pid);

    List<Dict> findByNameLike(String name);
}
