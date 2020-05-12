package com.bo.bookdb.service;

import com.bo.bookdb.entity.User;

import java.util.List;

/**
 * @author:hgt
 * @version:1.0
 * @date:2020/5/12
 * @description:com.bo.bookdb.service
 */
public interface UserService {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}
