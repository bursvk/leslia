package com.leslia.inter.service.impl;

import com.leslia.inter.api.BookService;
import com.leslia.inter.mapper.BookMapper;
import com.leslia.inter.pojo.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;



@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Transactional
    @Override
    public void insertBook(Book book) {
        bookMapper.insertBook(book);
    }

    @Override
    public void insertBook1(Book book) {
        this.insertBook(book);
    }


}
