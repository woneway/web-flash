package com.mtsearch.operation.dao.message;


import com.mtsearch.operation.bean.entity.message.Message;
import com.mtsearch.operation.dao.BaseRepository;

import java.util.ArrayList;


public interface MessageRepository extends BaseRepository<Message, Long> {
    void deleteAllByIdIn(ArrayList<String> list);
}

