package com.mtsearch.operation.service.test;


import com.mtsearch.operation.bean.entity.test.Boy;
import com.mtsearch.operation.dao.test.BoyRepository;
import com.mtsearch.operation.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoyService extends BaseService<Boy, Long, BoyRepository> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BoyRepository boyRepository;

}

