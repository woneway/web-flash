package com.mtsearch.operation.service.system;

import com.mtsearch.operation.bean.entity.system.Notice;
import com.mtsearch.operation.dao.system.NoticeRepository;
import com.mtsearch.operation.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * descript
 *
 * @author ：enilu
 * @date ：Created in 2019/6/30 11:14
 */
@Service
public class NoticeService extends BaseService<Notice, Long, NoticeRepository> {
    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> findByTitleLike(String title) {
        return noticeRepository.findByTitleLike("%" + title + "%");
    }
}
