package com.mtsearch.operation.service.test;


import com.mtsearch.operation.bean.entity.test.Girl;
import com.mtsearch.operation.dao.test.GirlRepository;

import com.mtsearch.operation.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GirlService extends BaseService<Girl,Long, GirlRepository> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private GirlRepository girlRepository;

}

