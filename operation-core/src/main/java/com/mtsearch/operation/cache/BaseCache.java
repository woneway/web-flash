package com.mtsearch.operation.cache;

import com.mtsearch.operation.bean.vo.SpringContextHolder;
import com.mtsearch.operation.service.system.impl.ConstantFactory;

/**
 * @author ：enilu
 * @date ：Created in 2020/4/26 19:07
 */
public abstract class BaseCache implements Cache {
    @Override
    public void cache() {
        SpringContextHolder.getBean(ConstantFactory.class).cleanLocalCache();
    }
}
