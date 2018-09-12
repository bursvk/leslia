package com.leslia.java.base;

import com.leslia.util.sequence.Sequence;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Base {


    /**
     * 冒泡排序法
     * @param aa
     * @return
     */
    public static int[] serial(int[] aa){
        int temp=0;
        for(int i=1;i<=aa.length;i++){
            for(int j=0;j<aa.length-i;j++){
                if(aa[j]>aa[j+1]){
                    temp=aa[j];
                    aa[j]=aa[j+1];
                    aa[j+1]=temp;
                }
            }
        }
        return aa;
    }


    /**
     * 二分查找法
     * @param aa
     * @param data
     * @return
     */
    public static int ere(int[] aa,int data){
        int start=0;
        int end=aa.length-1;
        int index=0;
        int between=(start+end)/2;
        while(start<=end){
            if(data>aa[between]){
                start=between+1;
            }
            if(data<aa[between]){
                end=between-1;
            }
            if(data==aa[between]){
                index=between;
                return index;
            }
            between=(start+end)/2;
        }
        index=-1;
        return index;
    }

    public static void log(int[] aa){
        for(int i=0;i<aa.length;i++){
            System.out.println(aa[i]);
        }
    }

    public static void main(String args[]){
        int[] aa={1,5,3,9,7,2};
        aa=serial(aa);
        log(aa);
        int index=ere(aa,2);
        System.out.println(index);
    }

    @Test
    public void test1(){
        Map<String,Object> map=new HashMap<>();
        map.put("one",1);
        map.put("two",2);
        map.put("three",3);
        for(Map.Entry<String,Object> entry:map.entrySet()){
            System.out.println(entry.getKey()+"  "+entry.getValue());
        }
    }


    @Test
    public void test2(){
        Sequence sequence=new Sequence(0,0);
        Long id=sequence.nextId();
        System.out.println(id);
    }






}
