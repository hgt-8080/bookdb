package com.bo.bookdb.service;

import com.bo.bookdb.entity.Book;

import java.util.List;

/**
 * @author:hgt
 * @version:1.0
 * @date:2020/5/12
 * @description:com.bo.bookdb.service
 */
public interface BookService {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    Book selectByPrimaryKey(Integer id);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);
}
