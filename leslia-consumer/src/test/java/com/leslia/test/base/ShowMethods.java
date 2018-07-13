package com.leslia.test.base;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

 public class ShowMethods {

    private static String usage= "usage:\n"+
            "ShowMethods qualified.class.name\n"+
            "To show all methods in classs or:\n"+
            "ShowMethods qualified.class.name word:\n"+
            "th search for methods involving 'word'";
    private static Pattern p=Pattern.compile("\\w+\\.");


    public int a;

    public static void main(String[] args){
        try {
            Class<?> c=Class.forName(ShowMethods.class.getName());
           // System.out.println(th)
            Method[] methods=c.getMethods();
            Constructor[] ctors=c.getConstructors();
            Field[] fields=c.getFields();
            for(Method method:methods){
                //System.out.println(method);
                System.out.println(p.matcher(method.toString()).replaceAll(""));
            }
            System.out.println("-------------------------------------");
            for(Constructor constructor:ctors){
                System.out.println(p.matcher(constructor.toString()).replaceAll(""));
            }
            System.out.println("-------------------------------------");
            for(Field field:fields){
                System.out.println(p.matcher(field.toString()).replaceAll(""));
            }
     /*       if(args.length==1){
                for(Method method:methods){
                    System.out.println(p.matcher(method.toString()).replaceAll(""));
                }
                for(Constructor ctor:ctors){
                    System.out.println(p.matcher(ctor.toString()).replaceAll(""));
                }
                lines=methods.length+ctors.length;
            }else{
                for(Method method:methods){
                    if(method.toString().indexOf(args[1])!=-1){
                        System.out.println(p.matcher(method.toString()).replaceAll(""));
                        lines++;
                    }
                }
                for(Constructor ctor:ctors){
                    if(ctor.toString().indexOf(args[-1])!=-1){
                        System.out.println(p.matcher(ctor.toString()).replaceAll(""));
                        lines++;
                    }
                }
            }*/
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}