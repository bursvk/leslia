package com.leslia.api.api;

import com.leslia.api.pojo.Book;

public interface BookService {

   public void insertBook(Book book);

   public void insertBook1(Book book);

   public Book selectBook(int bookId);

}
