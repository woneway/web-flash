package com.mtsearch.operation.dao.message;


import com.mtsearch.operation.bean.entity.message.MessageTemplate;
import com.mtsearch.operation.dao.BaseRepository;

import java.util.List;


public interface MessagetemplateRepository extends BaseRepository<MessageTemplate, Long> {
    MessageTemplate findByCode(String code);

    List<MessageTemplate> findByIdMessageSender(Long idMessageSender);
}

