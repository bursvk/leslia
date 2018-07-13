package com.leslia.test.inter;

import com.leslia.test.base.BaseTestLocal;
import com.leslia.inter.mapper.BookMapper;
import com.leslia.inter.pojo.Book;
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
