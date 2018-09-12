package com.leslia.service.mapper.impl;

import com.leslia.api.api.BookService;
import com.leslia.api.pojo.Book;
import com.leslia.service.mapper.BookMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;



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

    @Override
    public Book selectBook(int bookId){
        return bookMapper.selectBook(bookId);
    }


}
