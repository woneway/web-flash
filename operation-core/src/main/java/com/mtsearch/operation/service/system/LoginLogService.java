package com.mtsearch.operation.service.system;


import com.mtsearch.operation.bean.entity.system.LoginLog;
import com.mtsearch.operation.dao.system.LoginLogRepository;
import com.mtsearch.operation.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * Created  on 2018/3/26 0026.
 *
 * @author enilu
 */
@Service
public class LoginLogService extends BaseService<LoginLog, Long, LoginLogRepository> {

}
