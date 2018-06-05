package com.leslia.enums;

public class BaseView  {

    public static void main(String args[]){
       AllView[] view=AllView.values();
       for(AllView vv:view){
           System.out.println(vv);
           vv.aa();
       }
    }

}
