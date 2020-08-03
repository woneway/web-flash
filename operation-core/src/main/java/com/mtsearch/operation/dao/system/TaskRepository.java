package com.mtsearch.operation.dao.system;


import com.mtsearch.operation.bean.entity.system.Task;
import com.mtsearch.operation.dao.BaseRepository;

import java.util.List;

public interface TaskRepository extends BaseRepository<Task, Long> {

    long countByNameLike(String name);

    List<Task> findByNameLike(String name);

    List<Task> findAllByDisabled(boolean disable);
}
