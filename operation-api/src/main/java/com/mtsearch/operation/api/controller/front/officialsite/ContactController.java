package com.mtsearch.operation.api.controller.front.officialsite;

import com.mtsearch.operation.api.controller.BaseController;
import com.mtsearch.operation.bean.entity.cms.Contacts;
import com.mtsearch.operation.bean.vo.front.Rets;
import com.mtsearch.operation.service.cms.ContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/offcialsite/contact")
public class ContactController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ContactsService contactsService;

    @RequestMapping(method = RequestMethod.POST)
    public Object save(@Valid Contacts contacts) {
        contactsService.insert(contacts);
        return Rets.success();
    }
}
