package com.leslia.test.service;

import com.leslia.test.base.BaseTestLocal;
import com.leslia.service.mapper.BookMapper;
import com.leslia.api.pojo.Book;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

public class BookMapperTest extends BaseTestLocal{


    @Resource
    private BookMapper bookMapper;

    @Test
    public void insertBook(){
        Book book=new Book();
        book.setBookName("1");
        book.setAuthor("1");
        book.setCreateTime(new Date());
        bookMapper.insertBook(book);
    }


}
