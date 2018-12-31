package com.leslia.java.T;

public class TwoTuple<A,B> {

    public final A a;

    public final B b;

    public TwoTuple(A a,B b){
        this.a=a;
        this.b=b;
    }


    public static TwoTuple<Student,Book> getTwoTuple(){
        Student student=new Student();
        Book book=new Book();
        return new TwoTuple(student,book);
    }

    public static void main(String args[]){
        TwoTuple<Student,Book> tuple=TwoTuple.getTwoTuple();
        Student student=new Student();
    }

    Student student=new Student();

    public void test(){
        student.username();
    }

}
