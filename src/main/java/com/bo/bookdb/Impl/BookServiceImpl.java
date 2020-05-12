package com.bo.bookdb.Impl;

import com.bo.bookdb.dao.BookMapper;
import com.bo.bookdb.entity.Book;
import com.bo.bookdb.service.BookService;
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
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Book record) {
        return bookMapper.insert(record);
    }

    @Override
    public Book selectByPrimaryKey(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Book> selectAll() {
        return bookMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Book record) {
        return bookMapper.updateByPrimaryKey(record);
    }
}
