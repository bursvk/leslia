package com.leslia.java.T;

public class Holder3<U> {




    private U a;

    public  U getA() {
        return a;
    }

    public void setA(U a) {
        this.a = a;
    }

    public static <T>  void aa(T t){
        System.out.println(t);
    }

   /* public static <A,B,C> void aa(A ma,B mb,C mc){
        System.out.println(ma);
        System.out.println(mb);
        System.out.println(mc);
    }
*/
    public  <A,B> void aa(A ma,B mb,int mc){
        System.out.println(ma);
        System.out.println(mb);
        System.out.println(mc);
    }


    public static void main(String args[]){
        Holder3 holder3=new Holder3();
        //Holder3.aa(1,2,"hello");
        holder3.aa(1,2,3);
    }



}
