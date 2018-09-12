package com.leslia.service.mapper;


import com.leslia.api.pojo.Book;


public interface BookMapper {


    public void insertBook(Book book);

    public Book selectBook(int bookId);

}
