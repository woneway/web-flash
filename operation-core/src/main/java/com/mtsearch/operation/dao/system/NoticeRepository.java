package com.mtsearch.operation.dao.system;


import com.mtsearch.operation.bean.entity.system.Notice;
import com.mtsearch.operation.dao.BaseRepository;

import java.util.List;

/**
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
public interface NoticeRepository extends BaseRepository<Notice, Long> {
    List<Notice> findByTitleLike(String name);
}
