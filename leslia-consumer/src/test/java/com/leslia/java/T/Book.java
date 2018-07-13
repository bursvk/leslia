package com.leslia.java.T;

import java.util.ArrayList;
import java.util.List;

public final class Book {


    public  String  bookId;

    public  String bookName;

    public  String author;

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public <T> T getT(){
        return null;
    }

    public static <T> List<T> makeList(T ... args){
        List<T> list=new ArrayList<T>();
        for(T arg:args){
            list.add(arg);
        }
        return list;
    }

    public static Generator<Book> generator(){
        return new Generator<Book>() {
            @Override
            public Book next() {
                return null;
            }
        };
    }


    public static void main(String args[]){
        List<String> list=Book.makeList("hello","aaa","bbb");
        System.out.println(String.valueOf(list));
    }


}
