package com.mtsearch.operation.service.task;


import com.mtsearch.operation.bean.entity.system.TaskLog;
import com.mtsearch.operation.dao.system.TaskLogRepository;
import com.mtsearch.operation.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 定时任务日志服务类
 *
 * @author enilu
 * @date 2019-08-13
 */
@Service
public class TaskLogService extends BaseService<TaskLog, Long, TaskLogRepository> {
}
