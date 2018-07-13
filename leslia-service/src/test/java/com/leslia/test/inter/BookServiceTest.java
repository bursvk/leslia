package com.leslia.test.inter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.inter.api.BookService;
import com.leslia.inter.pojo.Book;
import com.leslia.test.base.BaseTestDubbo;
import org.junit.Test;

import java.util.Date;

public class BookServiceTest extends BaseTestDubbo {

    @Reference
    private BookService bookService;

    @Test
    public void insertBook(){
        Book book=new Book();
        book.setBookName("神奇校车");
        book.setAuthor("乔安娜柯尔");
        book.setCreateTime(new Date());
        bookService.insertBook1(book);
    }



}
