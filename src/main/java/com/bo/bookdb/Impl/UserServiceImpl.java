package com.bo.bookdb.Impl;

import com.bo.bookdb.dao.UserMapper;
import com.bo.bookdb.entity.User;
import com.bo.bookdb.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:hgt
 * @version:1.0
 * @date:2020/5/12
 * @description:com.bo.bookdb.Impl
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }
}
