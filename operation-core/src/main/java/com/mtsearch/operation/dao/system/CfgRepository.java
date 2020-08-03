package com.mtsearch.operation.dao.system;

import com.mtsearch.operation.bean.entity.system.Cfg;
import com.mtsearch.operation.dao.BaseRepository;

/**
 * 全局参数dao
 *
 * @author ：enilu
 * @date ：Created in 2019/6/29 12:50
 */
public interface CfgRepository extends BaseRepository<Cfg, Long> {

    Cfg findByCfgName(String cfgName);
}
