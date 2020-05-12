package com.bo.bookdb.controller;

import com.bo.bookdb.Impl.BookServiceImpl;
import com.bo.bookdb.entity.Book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:hgt
 * @version:1.0
 * @date:2020/5/12
 * @description:com.bo.bookdb.controller
 */
@RestController
public class test {
    @Resource
    private BookServiceImpl bi;

    @RequestMapping("/getBookById")
    public String getAuthClient(@RequestParam(value = "id") Integer id){
        Book b = bi.selectByPrimaryKey(id);
        return b.getAuthor();
    }
}
