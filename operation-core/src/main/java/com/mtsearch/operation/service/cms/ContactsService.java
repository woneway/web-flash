package com.mtsearch.operation.service.cms;

import com.mtsearch.operation.bean.entity.cms.Contacts;
import com.mtsearch.operation.dao.cms.ContactsRepository;
import com.mtsearch.operation.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ContactsService extends BaseService<Contacts, Long, ContactsRepository> {
}
