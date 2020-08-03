package com.mtsearch.operation.dao.system;


import com.mtsearch.operation.bean.entity.system.User;
import com.mtsearch.operation.dao.BaseRepository;

/**
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
public interface UserRepository extends BaseRepository<User, Long> {
    User findByAccount(String account);

    User findByAccountAndStatusNot(String account, Integer status);
}
