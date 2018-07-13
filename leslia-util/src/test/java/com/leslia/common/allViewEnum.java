package com.leslia.common;

import com.leslia.enums.AllView;

import java.util.*;

interface Command{
    public void aa();
    public void bb();
}

public class allViewEnum {

    public static void main(String args[]){

        //EnumSet
        EnumSet<AllView> allSet=EnumSet.noneOf(AllView.class);
        allSet.add(AllView.init);
        System.out.println(allSet);
        allSet.add(AllView.end);
        allSet.add(AllView.between);
        System.out.println(allSet);

        //EnumMap
        EnumMap<AllView,Command> enumMap=new EnumMap<AllView, Command>(AllView.class);
        enumMap.put(AllView.init, new Command() {
            public void aa() {
                System.out.println("aa");
            }
            public void bb(){
                System.out.println("bb");
            }
        });
        enumMap.put(AllView.between, new Command() {
            public void aa() {
                System.out.println("bb");
            }
            public void bb(){
                System.out.println("bbbb");
            }
        });

        for(Map.Entry<AllView,Command> ee:enumMap.entrySet()){
            System.out.print(ee.getKey());
            ee.getValue().aa();
            ee.getValue().bb();
        }


    }

}
