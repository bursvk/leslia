package com.leslia.java.test;

public class Label {

    public static void main(String args[]){
        int i=0;
        label1:
        for(int j=0;j<10;j++){
            System.out.println("J:"+j);
            label2:
            for(;i<10;i++){
                System.out.println(i);
                if(i==4){
                    break label2;
                }
            }
        }
        System.out.println("我退出了");
    }
}
